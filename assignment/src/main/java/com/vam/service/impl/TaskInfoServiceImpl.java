package com.vam.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.vam.dao.TaskInfoMapper;
import com.vam.dmo.TaskInfoDmo;
import com.vam.dmo.TaskTypeDmo;
import com.vam.dto.TaskInfoDto;
import com.vam.dto.TaskListDto;
import com.vam.exec.ExecutorIntf;
import com.vam.exec.ExecutorProxy;
import com.vam.service.intf.*;
import com.vam.util.VamConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

@Service
@Slf4j
public class TaskInfoServiceImpl implements TaskInfoService {
    @Autowired
    private TaskInfoMapper taskInfoMapper;
    @Autowired
    private LockLogService lockLogService;
    @Autowired
    private ThreadCountService threadCountService;
    @Autowired
    private ExceptionService exceptionService;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private ExecutorProxy executorProxy;

    @Override
    public void processTaskTimeout() {
        TaskInfoDmo record = new TaskInfoDmo();
        record.setTimeoutTime(new Date());
        while (true) {
            PageHelper.startPage(1, VamConstants.PAGE_SIZE);

            List<TaskInfoDmo> taskInfoDmos = taskInfoMapper.selectRunningTaskOfTimeOut(record);
            if (CollectionUtils.isEmpty(taskInfoDmos)) {
                break;
            }
            log.info("超时任务:{}", taskInfoDmos);
            taskInfoMapper.batchUpdateRunStatusByPrimaryKey(taskInfoDmos);
        }
    }

    @Override
    public int deleteById(Long id) {
        return taskInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void assignTask(TaskTypeDmo taskTypeDmo) {
        log.debug("开始{}类型的任务分派", taskTypeDmo.getBizType());
        if (taskTypeDmo.getStatus() == VamConstants.TASK_STOP_TYPE) {
            return;
        }
        Long lockId = lockLogService.lock(taskTypeDmo.getBizType(), VamConstants.DEFAULT_LOCK_TIMEOUT_MIN);
        if (lockId == null) {
            log.info("当前线程未获取锁，不能执行分派");
            return;
        }
        try {
            log.info("开始执行{}任务！", taskTypeDmo.getBizType());
            while (threadCountService.countByType(taskTypeDmo.getBizType()) < taskTypeDmo.getPerThreadCount()) {
                Long threadId = threadCountService.add(taskTypeDmo.getBizType());
                List<TaskInfoDmo> taskInfoDmos = getWaitingTaskOfTimeComing(taskTypeDmo.getBizType());
                if (CollectionUtils.isEmpty(taskInfoDmos)) {
                    log.info("没有任务！");
                    threadCountService.delete(threadId);
                    return;
                }
                List<TaskInfoDto> taskInfoDtos = new ArrayList<>(taskInfoDmos.size());
                Iterator<TaskInfoDmo> iterator = taskInfoDmos.iterator();
                while (iterator.hasNext()) {
                    TaskInfoDmo taskInfoDmo = iterator.next();
                    if (dealTaskMove(taskTypeDmo, taskInfoDmo)) {
                        iterator.remove();
                    } else {
                        taskInfoDtos.add(TaskInfoDto.from(taskInfoDmo));
                    }
                }
                if (CollectionUtils.isEmpty(taskInfoDmos)) {
                    log.debug("没有可执行任务！");
                    threadCountService.delete(threadId);
                    return;
                }
                try {
                    batchUpdateWaitingTasks(taskInfoDmos, taskTypeDmo);
                    log.debug("准备执行任务:{}", taskInfoDmos);
                } catch (Exception e) {
                    log.error("", e);
                    return;
                }
                ExecutorIntf executorIntf = executorProxy.getExecutor(taskTypeDmo.getExecType());
                if (executorIntf == null) {
                    throw new RuntimeException("任务执行器配置错误");
                }
                TaskListDto taskListDto = new TaskListDto();
                taskListDto.setBizType(taskTypeDmo.getBizType());
                taskListDto.setThreadId(threadId);
                taskListDto.setTaskInfoDtoList(taskInfoDtos);
                taskListDto.setProcessorName(taskTypeDmo.getExecService());
                executorIntf.process(taskListDto);
            }
        } finally {
            lockLogService.unLock(lockId);
        }
    }

    /**
     * 任务调度前批量更新任务状态，任务下次执行时间，超时时间
     *
     * @param taskInfoDmos
     * @param taskTypeDmo
     */
    private void batchUpdateWaitingTasks(final List<TaskInfoDmo> taskInfoDmos, final TaskTypeDmo taskTypeDmo) {
        final Map<Integer, List<TaskInfoDmo>> taskGroup = groupByRunTimes(taskInfoDmos);
        transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                int result = 0;
                for (Map.Entry<Integer, List<TaskInfoDmo>> entry : taskGroup.entrySet()) {
                    Date nextRunTime = getNextRunTime(taskTypeDmo, entry.getKey());
                    Date timeoutTime = DateUtils.addMinutes(nextRunTime, taskTypeDmo.getTimeoutMinutes());
                    Map<String, Object> paraMap = new HashMap<>(8);
                    paraMap.put("list", entry.getValue());
                    paraMap.put("nextRunTime", nextRunTime);
                    paraMap.put("timeoutTime", timeoutTime);
                    result += taskInfoMapper.batchUpdateWaitingTasks(paraMap);
                }
                if (result != taskInfoDmos.size()) {
                    log.warn("{}更新为处理中失败,需要更新{}条，实际更新{}条", taskTypeDmo.getBizType(), taskInfoDmos.size(), result);
                    throw new RuntimeException("更新失败，回滚任务");
                }
                return result;
            }
        });
    }


    private List<TaskInfoDmo> getWaitingTaskOfTimeComing(String bizType) {
        TaskInfoDmo record = new TaskInfoDmo();
        record.setBizType(bizType);
        record.setNextRunTime(new Date());
        PageHelper.startPage(1, VamConstants.PAGE_SIZE);
        return taskInfoMapper.selectWaitingTaskOfTimeComing(record);
    }

    /**
     * 当前任务类型下次执行时间
     */
    private Date getNextRunTime(TaskTypeDmo taskTypeDmo, Integer runCount) {
        String runIntervals = taskTypeDmo.getRunIntervals();
        if (StringUtils.isBlank(runIntervals)) {
            return DateTime.now().plusMillis(VamConstants.INTERVAL_TIME_DEFAULT).toDate();
        }
        List<String> timeList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(runIntervals);
        int index;
        if (runCount >= timeList.size()) {
            index = timeList.size() - 1;
        } else {
            index = runCount;
        }
        return DateTime.now().plusMillis(Integer.parseInt(timeList.get(index))).toDate();
    }


    @Override
    public void moveToAnotherType(TaskInfoDmo record, TaskTypeDmo taskTypeDmo) {
        Preconditions.checkNotNull(record);
        Preconditions.checkArgument(taskTypeDmo != null && StringUtils.isNotBlank(taskTypeDmo.getBizType()));
        TaskInfoDmo taskInfoDmo = new TaskInfoDmo();
        taskInfoDmo.setBizKey(record.getBizKey());
        taskInfoDmo.setBizType(taskTypeDmo.getBizType());
        taskInfoDmo.setCreateTime(record.getCreateTime());
        taskInfoDmo.setLastUpdateTime(new Date());
        taskInfoDmo.setStatus(VamConstants.TASK_STATUS_NEW);
        taskInfoDmo.setNextRunTime(DateTime.now().plusMillis(taskTypeDmo.getDelayedRunMinutes()).toDate());
        taskInfoDmo.setRunCount(0);
        try {
            taskInfoMapper.insert(taskInfoDmo);
        } catch (DuplicateKeyException e) {
            log.warn("表中该任务已存在,{},{}", record.getBizKey(), record.getBizType());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() != null && e.getMessage().contains("DuplicateKeyException")) {
                log.warn("表中该任务已存在,{},{}", record.getBizKey(), record.getBizType());
            } else {
                throw e;
            }
        }
        taskInfoMapper.deleteByPrimaryKey(record.getId());
    }


    private Map<Integer, List<TaskInfoDmo>> groupByRunTimes(List<TaskInfoDmo> taskInfoDmos) {
        Map<Integer, List<TaskInfoDmo>> result = new HashMap<>(8);
        for (TaskInfoDmo taskInfoDmo : taskInfoDmos) {
            List<TaskInfoDmo> each = result.get(taskInfoDmo.getRunCount());
            if (each == null) {
                each = new ArrayList<>();
                result.put(taskInfoDmo.getRunCount(), each);
            }
            each.add(taskInfoDmo);
        }
        log.debug("任务分组结果:{}", result);
        return result;
    }

    @Override
    public boolean dealTaskMove(TaskTypeDmo taskTypeDmo, TaskInfoDmo taskInfoDmo) {
        if (taskInfoDmo.getRunCount() < taskTypeDmo.getMaxRunTimes()) {
            return false;
        }
        if (taskTypeDmo.getExceptionProcType() == VamConstants.EXCEPTION_TYPE_NEW_TYPE
                && StringUtils.isNotBlank(taskTypeDmo.getExceptionProc())) {
            moveToAnotherType(taskInfoDmo, taskTypeDmo);
        } else {
            exceptionService.moveToException(taskInfoDmo);
        }
        return true;
    }


    @Override
    public TaskInfoDmo selectByPrimaryKey(Long id) {
        return taskInfoMapper.selectByPrimaryKey(id);
    }
}
