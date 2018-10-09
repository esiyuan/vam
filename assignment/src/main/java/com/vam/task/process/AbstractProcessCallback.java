package com.vam.task.process;

import com.google.common.base.Preconditions;
import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.dto.ProcessStatusEnum;
import com.vam.task.dto.TaskListResultDto;
import com.vam.task.dto.TaskResultDto;
import com.vam.task.service.intf.TaskInfoService;
import com.vam.task.service.intf.TaskTypeService;
import com.vam.task.service.intf.ThreadCountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractProcessCallback implements ProcessCallback {
    @Autowired
    private ThreadCountService threadCountService;
    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private TaskTypeService taskTypeService;

    @Override
    public void handlerResult(TaskListResultDto listResultDto) {
        log.info("处理器返回：{}", listResultDto);
        Preconditions.checkNotNull(listResultDto);
        if (CollectionUtils.isEmpty(listResultDto.getTaskResultDtoList()) || StringUtils.isBlank(listResultDto.getBizType())) {
            return;
        }
        try {
            TaskTypeDmo taskTypeDmo = taskTypeService.selectByBizType(listResultDto.getBizType());
            Preconditions.checkNotNull(taskTypeDmo);
            for (TaskResultDto taskResultDto : listResultDto.getTaskResultDtoList()) {
                if (taskResultDto.getProcessStatus() == ProcessStatusEnum.SUCCESS) {
                    log.debug("任务{}执行成功", taskResultDto);
                    taskInfoService.deleteById(taskResultDto.getId());
                    continue;
                }
                TaskInfoDmo taskInfoDmo = taskInfoService.selectByPrimaryKey(taskResultDto.getId());
                Preconditions.checkNotNull(taskInfoDmo);
                taskInfoService.dealTaskMove(taskTypeDmo, taskInfoDmo);
            }
        } finally {
            threadCountService.delete(listResultDto.getThreadId());
        }
    }
}
