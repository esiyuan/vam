package com.vam.task.dmo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class TaskInfoDmo {
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