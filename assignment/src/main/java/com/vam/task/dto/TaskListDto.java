package com.vam.task.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
public class TaskListDto implements Serializable {
    private static final long serialVersionUID = 64542565033298269L;

    private List<TaskInfoDto> taskInfoDtoList;
    private Long threadId;
    private String bizType;
    private String processorName;

}
