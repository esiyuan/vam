package com.vam.facade;

import com.vam.service.ServiceConfig;
import com.vam.task.dto.TaskInfoDto;
import com.vam.task.facade.TaskInfoFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class TaskInfoFacadeTest {

    @Autowired
    private TaskInfoFacade taskInfoFacade;

    @Test
    public void testInsert() {
        for (int i = 1000; i < 1100; i++) {
            TaskInfoDto record = new TaskInfoDto();
            record.setBizType("type007");
            record.setBizKey("" + i);
            taskInfoFacade.insert(record);
        }
    }
}
