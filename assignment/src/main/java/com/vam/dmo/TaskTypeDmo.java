package com.vam.dmo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskTypeDmo {
    private Long id;

    private String bizType;

    private Integer maxRunTimes;

    private String runIntervals;

    private Integer delayedRunMinutes;

    private String execType;

    private String execService;

    private String execMethod;

    private Integer perPageCount;

    private Integer perThreadCount;

    private Integer timeoutMinutes;

    private Integer exceptionProcType;

    private String exceptionProc;

    private Integer status;

}