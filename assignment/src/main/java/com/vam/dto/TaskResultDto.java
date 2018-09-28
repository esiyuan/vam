package com.vam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class TaskResultDto implements Serializable {

    private static final long serialVersionUID = 3338164073047736180L;
    private Long id;
    private String bizKey;
    private String bizType;
    private ProcessStatusEnum processStatus;

    public static TaskResultDto from(TaskInfoDto taskInfoDto) {
        TaskResultDto taskResultDto = new TaskResultDto();
        taskResultDto.setBizKey(taskInfoDto.getBizKey());
        taskResultDto.setBizType(taskInfoDto.getBizType());
        taskResultDto.setId(taskInfoDto.getId());
        return taskResultDto;
    }
}
