package com.vam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Setter
@Getter
public class TaskListResultDto implements Serializable {

    private static final long serialVersionUID = 3305801622420878979L;

    private List<TaskResultDto> taskResultDtoList;

    private Long threadId;

    private String bizType;
}
