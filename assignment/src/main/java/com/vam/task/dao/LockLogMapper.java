package com.vam.task.dao;

import com.vam.task.dmo.LockLogDmo;

import java.util.List;
import java.util.Map;

public interface LockLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(LockLogDmo record);

    /**
     * 批量删除超时锁
     *
     * @param lockLogs
     * @return
     */
    int batchDeleteByPrimaryKey(List<LockLogDmo> lockLogs);

    /**
     * 获取超时的锁
     *
     * @param record
     * @return 超时锁Id集合
     */
    List<LockLogDmo> selectTimeoutIds(LockLogDmo record);

    LockLogDmo selectByPrimaryKey(Long id);


    LockLogDmo selectByLockType(String lockType);

    int updateByPrimaryKeySelective(LockLogDmo record);

    int updateByPrimaryKey(LockLogDmo record);

    /**
     * cas更新锁的状态
     * @param param
     * @return
     */
    int updateStatusCAS(Map<String, Object> param);
}