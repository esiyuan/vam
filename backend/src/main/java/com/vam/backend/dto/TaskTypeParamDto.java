package com.vam.backend.dto;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.util.VamConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

@Setter
@Getter
@ToString
public class TaskTypeParamDto {
    private Long id;
    private String bizType;

    private Integer maxRunTimes;

    private String execType;
    private String execService;
    private String execMethod;
    private String runIntervals;

    private Integer delayedRunMinutes;

    private Integer perPageCount;

    private Integer perThreadCount;

    private Integer timeoutMinutes;

    private Integer exceptionProcType;

    private String exceptionProc;

    private Integer status;


    public TaskTypeDmo toTaskTypeDmo() {
        TaskTypeDmo taskTypeDmo = new TaskTypeDmo();
        taskTypeDmo.setId(id);
        taskTypeDmo.setBizType(bizType);
        taskTypeDmo.setMaxRunTimes(ObjectUtils.defaultIfNull(maxRunTimes, 1));
        taskTypeDmo.setExecType(execType);
        taskTypeDmo.setExecService(execService);
        taskTypeDmo.setExecMethod(execMethod);
        taskTypeDmo.setRunIntervals(ObjectUtils.defaultIfNull(runIntervals, "1"));
        taskTypeDmo.setDelayedRunMinutes(ObjectUtils.defaultIfNull(delayedRunMinutes, 1));
        taskTypeDmo.setPerPageCount(ObjectUtils.defaultIfNull(perPageCount, 10));
        taskTypeDmo.setPerThreadCount(ObjectUtils.defaultIfNull(perThreadCount, 10));
        taskTypeDmo.setTimeoutMinutes(ObjectUtils.defaultIfNull(timeoutMinutes, 5));
        taskTypeDmo.setExceptionProc(exceptionProc);
        taskTypeDmo.setExceptionProcType(ObjectUtils.defaultIfNull(exceptionProcType, 0));
        taskTypeDmo.setStatus(ObjectUtils.defaultIfNull(status, VamConstants.TASK_START_TYPE));
        return taskTypeDmo;
    }

}
