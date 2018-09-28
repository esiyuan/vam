package com.vam.service;

import com.vam.DaoConfig;
import com.vam.service.intf.LockLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class LockLogServiceTest {
    @Autowired
    private LockLogService lockLogService;

    @Test
    public void lock() {
        lockLogService.lock("biz001", 5);
    }


}
