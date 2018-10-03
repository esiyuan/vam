package com.vam.task.dto;

import com.vam.task.dmo.TaskInfoDmo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class TaskInfoDto implements Serializable {
    private static final long serialVersionUID = -8428348915360873984L;
    private Long id;
    private String bizKey;
    private String bizType;


    public static TaskInfoDto from(TaskInfoDmo taskInfoDmo) {
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        taskInfoDto.setId(taskInfoDmo.getId());
        taskInfoDto.setBizType(taskInfoDmo.getBizType());
        taskInfoDto.setBizType(taskInfoDmo.getBizType());
        return taskInfoDto;
    }

}
