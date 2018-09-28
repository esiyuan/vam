package com.vam.dmo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class LockLogDmo {
    private Long id;

    private String lockType;

    private Date createTime;

    private Date timeoutTime;

    private Integer status;


}