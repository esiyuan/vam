package com.vam.service;

import com.vam.task.service.intf.TaskInfoService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class TaskInfoServiceTest {
    @Autowired
    private TaskInfoService taskInfoService;



}
