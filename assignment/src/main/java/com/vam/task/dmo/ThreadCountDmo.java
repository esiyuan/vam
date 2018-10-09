package com.vam.task.dmo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class ThreadCountDmo {
    private Long id;

    private String bizType;

    private Date createTime;

    private Date timeoutTime;

}