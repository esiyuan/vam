package com.vam.dto;

import java.io.Serializable;

public enum  ProcessStatusEnum implements Serializable{
    /**
     * 失败
     */
    FAIL,
    /**
     * 成功
     */
    SUCCESS,
    /**
     * 终止
     */
    TERMINATE;


}
