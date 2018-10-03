package com.vam.task.process;

import com.vam.task.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class AbstraceTaskProcessor implements TaskProcessIntf {

    @Override
    public TaskListResultDto process(TaskListDto taskListDto) {
        log.info("任务分派 :{}", taskListDto);
        TaskListResultDto taskListResultDto = new TaskListResultDto();
        taskListResultDto.setBizType(taskListDto.getBizType());
        taskListResultDto.setBizType(taskListDto.getBizType());
        taskListResultDto.setThreadId(taskListDto.getThreadId());
        List<TaskInfoDto> taskInfoDtos = taskListDto.getTaskInfoDtoList();
        if (CollectionUtils.isEmpty(taskInfoDtos)) {
            taskListResultDto.setTaskResultDtoList(Collections.EMPTY_LIST);
            return taskListResultDto;
        }

        List<TaskResultDto> taskResultDtos = new ArrayList<>(taskInfoDtos.size());
        for (TaskInfoDto taskInfoDto : taskInfoDtos) {
            TaskResultDto taskResultDto = TaskResultDto.from(taskInfoDto);
            ProcessStatusEnum processStatus;
            try {
                processStatus = execTask(taskInfoDto);
            } catch (Exception e) {
                log.error("任务{}执行失败", taskInfoDto, e);
                processStatus = ProcessStatusEnum.FAIL;
            }
            taskResultDto.setProcessStatus(processStatus);
            taskResultDtos.add(taskResultDto);
        }
        taskListResultDto.setTaskResultDtoList(taskResultDtos);
        return taskListResultDto;
    }


    protected abstract ProcessStatusEnum execTask(TaskInfoDto taskInfoDto);

}
