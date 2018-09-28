package com.vam.dmo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class TaskInfoDmo {

    public final static int RUNNING = 1;
    public final static int WAITING = 0;

    private Long id;

    private String bizKey;

    private String bizType;

    private Integer runCount;

    private Date nextRunTime;

    private Date createTime;

    private Date lastUpdateTime;

    private Integer status;

    private Date timeoutTime;

}