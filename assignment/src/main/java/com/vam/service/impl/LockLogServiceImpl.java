package com.vam.service.impl;

import com.github.pagehelper.PageHelper;
import com.vam.dao.LockLogMapper;
import com.vam.dmo.LockLogDmo;
import com.vam.service.intf.LockLogService;
import com.vam.util.VamConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LockLogServiceImpl implements LockLogService {

    @Autowired
    private LockLogMapper lockLogMapper;

    @Override
    public void unLock(long id) {
        Map<String, Object> param = new HashMap<>(8);
        param.put("id", id);
        param.put("oldStatus", VamConstants.LOCKLOG_STATUS_IN_USE);
        param.put("newStatus", VamConstants.LOCKLOG_STATUS_IDLE);
        lockLogMapper.updateStatusCAS(param);
    }

    @Override
    public void processTimeOut() {
        LockLogDmo record = new LockLogDmo();
        record.setTimeoutTime(new Date());
        PageHelper.startPage(1, VamConstants.PAGE_SIZE);
        List<LockLogDmo> lockLogs = lockLogMapper.selectTimeoutIds(record);
        if (CollectionUtils.isEmpty(lockLogs)) {
            return;
        }
        log.info("超时锁:{}", lockLogs);
        for (LockLogDmo lockLog : lockLogs) {
            unLock(lockLog.getId());
        }

    }

    @Override
    public Long lock(String lockType, int timeoutMinutes) {
        log.debug("开始获取锁 {}", lockType);
        LockLogDmo lockLog = lockLogMapper.selectByLockType(lockType);
        if (lockLog == null) {
            try {
                return addLockLog(lockType, timeoutMinutes);
            } catch (Exception e) {
                log.error("并发初始化锁失败,如果是唯一键冲突错误，请忽视", e);
                return null;
            }
        }
        if (lockLog.getStatus() == VamConstants.LOCKLOG_STATUS_IDLE) {
            Map<String, Object> param = new HashMap<>(8);
            param.put("id", lockLog.getId());
            param.put("newStatus", VamConstants.LOCKLOG_STATUS_IN_USE);
            param.put("oldStatus", VamConstants.LOCKLOG_STATUS_IDLE);
            if (lockLogMapper.updateStatusCAS(param) == 1) {
                return lockLog.getId();
            }
        }
        return null;
    }

    private Long addLockLog(String lockType, int timeoutMinutes) {
        LockLogDmo record = new LockLogDmo();
        record.setLockType(lockType);
        record.setStatus(VamConstants.LOCKLOG_STATUS_IDLE);
        DateTime now = DateTime.now();
        record.setCreateTime(now.toDate());
        record.setTimeoutTime(now.plusMillis(timeoutMinutes).toDate());
        lockLogMapper.insert(record);
        return record.getId();
    }

}
