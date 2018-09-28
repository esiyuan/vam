package com.vam.service.intf;

public interface LockLogService {

    /**
     * 清理超时的锁
     */
    void processTimeOut();

    /**
     * 释放锁
     *
     * @param id
     */
    void unLock(long id);

    /**
     * 获取锁
     *
     * @param lockType
     * @param timeoutMinutes
     * @return
     */
    public Long lock(String lockType, int timeoutMinutes);
}
