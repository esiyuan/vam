package com.vam.dao;

import com.vam.DaoConfig;
import com.vam.dmo.TaskTypeDmo;
import com.vam.util.VamConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class TaskTypeMapperTest {
    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Test
    public void selectAll() {
        System.out.println(taskTypeMapper.selectAll());
    }

    @Test
    public void insertTest() {
        TaskTypeDmo taskTypeDmo = new TaskTypeDmo();
        taskTypeDmo.setBizType("biz001");
        taskTypeDmo.setDelayedRunMinutes(1);
        taskTypeDmo.setExceptionProc("biz001");
        taskTypeDmo.setExceptionProcType(0);
        taskTypeDmo.setExecType("springBeanExecutor");
        taskTypeDmo.setExecService("simpleTaskProcess");
        taskTypeDmo.setExecMethod("process");
        taskTypeDmo.setMaxRunTimes(10);
        taskTypeDmo.setPerPageCount(10);
        taskTypeDmo.setPerThreadCount(10);
        taskTypeDmo.setStatus(VamConstants.TASK_STATUS_NEW);
        taskTypeDmo.setRunIntervals("1");
        taskTypeDmo.setTimeoutMinutes(10);
        taskTypeMapper.insert(taskTypeDmo);
    }
}
