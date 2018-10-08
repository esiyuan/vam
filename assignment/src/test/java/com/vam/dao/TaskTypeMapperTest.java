package com.vam.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vam.task.dao.TaskTypeMapper;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.util.VamConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-dao.xml"})
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

    @Test
    public void search() {
        TaskTypeDmo taskTypeDmo = new TaskTypeDmo();
//        taskTypeDmo.setBizType("biz001");
//        taskTypeDmo.setDelayedRunMinutes(1);
//        taskTypeDmo.setExceptionProc("biz001");
//        taskTypeDmo.setExceptionProcType(0);
//        taskTypeDmo.setExecType("springBeanExecutor");
//        taskTypeDmo.setExecService("simpleTaskProcess");
//        taskTypeDmo.setExecMethod("process");
//        taskTypeDmo.setMaxRunTimes(10);
//        taskTypeDmo.setPerPageCount(10);
//        taskTypeDmo.setPerThreadCount(10);
//        taskTypeDmo.setStatus(VamConstants.TASK_STATUS_NEW);
//        taskTypeDmo.setRunIntervals("1");
//        taskTypeDmo.setTimeoutMinutes(10);
        PageHelper.startPage(1, 10);

        Page<TaskTypeDmo> taskTypeDmos = taskTypeMapper.search(taskTypeDmo);
        System.out.println(taskTypeDmos);
        System.out.println(Arrays.toString(taskTypeDmos.toArray(new TaskTypeDmo[taskTypeDmos.size()])));
    }
}
