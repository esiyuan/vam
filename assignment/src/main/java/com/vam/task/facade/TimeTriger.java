package com.vam.task.facade;

import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.service.intf.TaskInfoService;
import com.vam.task.service.intf.TaskTypeService;
import com.vam.task.util.ExecutorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TimeTriger {
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private TimeoutFacade timeoutFacade;
    @Autowired
    private TaskInfoService taskInfoService;

    @Scheduled(fixedRate = 30000)
    public void timeTrigger() {
        timeoutFacade.processTimeOut();
        List<TaskTypeDmo> taskTypeDmos = taskTypeService.selectAll();
        if (CollectionUtils.isEmpty(taskTypeDmos)) {
            log.warn("没有定义任务类型，组件不进行任务分派.");
        }
        TaskTypeDmo[] taskTypeArrays = taskTypeDmos.toArray(new TaskTypeDmo[taskTypeDmos.size()]);
        if ((System.currentTimeMillis() & 1) == 1) {
            CollectionUtils.reverseArray(taskTypeArrays);
        }
        for (final TaskTypeDmo taskTypeDmo : taskTypeArrays) {
            ExecutorUtil.execute(new Runnable() {
                @Override
                public void run() {
                    taskInfoService.assignTask(taskTypeDmo);
                }
            });
        }
    }
}
