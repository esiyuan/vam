package com.vam.util;

public class VamConstants {

    /**
     * 不知道页数默认查询条数
     */
    public static final int PAGE_SIZE = 500;
    /**
     * 锁空闲状态
     */
    public static final int LOCKLOG_STATUS_IDLE = 0;
    /**
     * 锁被占用状态
     */
    public static final int LOCKLOG_STATUS_IN_USE = 1;
    /**
     * 任务关闭状态
     */
    public static final int TASK_STOP_TYPE = 0;
    /**
     * 任务打开状态
     */
    public static final int TASK_START_TYPE = 1;
    public static final String ASYN_PROCESS_LOCK = "asynProcessTimeOutTask";

    /**
     * 锁超时默认时间
     */
    public static final int DEFAULT_LOCK_TIMEOUT_MIN = 5;

    /**
     * 默认异常处理方式
     */
    public static final int EXCEPTION_TYPE_DEFAULT = 0;
    /**
     * 异常处理方式 新增其他任务类型
     */
    public static final int EXCEPTION_TYPE_NEW_TYPE = 1;

    /**
     * 任务状态 新建
     */
    public static final int TASK_STATUS_NEW = 0;
    /**
     * 任务状态 处理中
     */
    public static final int TASK_STATUS_PROCESSING = 1;
    /**
     * 如果不配置，任务补偿默认延时1分钟
     */
    public static final int INTERVAL_TIME_DEFAULT = 1;

}
