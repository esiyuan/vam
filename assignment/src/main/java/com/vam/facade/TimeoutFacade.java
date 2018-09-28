package com.vam.facade;

import com.vam.service.intf.LockLogService;
import com.vam.service.intf.TaskInfoService;
import com.vam.service.intf.ThreadCountService;
import com.vam.util.ExecutorUtil;
import com.vam.util.VamConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 清理超时资源
 */
@Component
@Slf4j
public class TimeoutFacade {
    @Autowired
    private LockLogService lockLogService;
    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private ThreadCountService threadCountService;

    public void processTimeOut() {
        lockLogService.processTimeOut();
        asynProcessTimeOutTask();
        threadCountService.processTimeout();
    }

    /**
     * 异步处理超时任务
     */
    private void asynProcessTimeOutTask() {
        ExecutorUtil.execute(new Runnable() {
            @Override
            public void run() {
                Long lockId = lockLogService.lock(VamConstants.ASYN_PROCESS_LOCK, VamConstants.DEFAULT_LOCK_TIMEOUT_MIN);
                if (lockId == null) {
                    return;
                }
                try {
                    taskInfoService.processTaskTimeout();
                } finally {
                    lockLogService.unLock(lockId);
                }
            }
        });
    }


}
