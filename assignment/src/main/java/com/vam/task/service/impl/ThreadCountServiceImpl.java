package com.vam.task.service.impl;

import com.github.pagehelper.PageHelper;
import com.vam.task.dao.ThreadCountMapper;
import com.vam.task.dmo.ThreadCountDmo;
import com.vam.task.service.intf.ThreadCountService;
import com.vam.task.util.VamConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ThreadCountServiceImpl implements ThreadCountService {
    @Autowired
    private ThreadCountMapper threadCountMapper;

    @Override
    public int countByType(String bizType) {
        return threadCountMapper.countByType(bizType);
    }

    @Override
    public Long add(String bizType) {
        ThreadCountDmo threadCountDmo = new ThreadCountDmo();
        threadCountDmo.setBizType(bizType);
        DateTime now = DateTime.now();
        threadCountDmo.setCreateTime(now.toDate());
        threadCountDmo.setTimeoutTime(now.plusMinutes(VamConstants.DEFAULT_LOCK_TIMEOUT_MIN).toDate());
        threadCountMapper.insert(threadCountDmo);
        return threadCountDmo.getId();
    }

    @Override
    public void delete(Long threadId) {
        threadCountMapper.deleteByPrimaryKey(threadId);
    }

    @Override
    public void processTimeout() {
        ThreadCountDmo record = new ThreadCountDmo();
        record.setTimeoutTime(new Date());
        PageHelper.startPage(1, VamConstants.PAGE_SIZE);
        List<ThreadCountDmo> threadCountDmos = threadCountMapper.selectThreadCountOfTimeOut(record);
        if (CollectionUtils.isEmpty(threadCountDmos)) {
            return;
        }
        log.info("超时的记录 : {}", threadCountDmos);
        threadCountMapper.batchDeleteByPrimaryKey(threadCountDmos);

    }

}
