package com.vam.facade;

import com.vam.task.facade.TimeTriger;
import com.vam.service.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class TimeTrigerTest {
    @Autowired
    private TimeTriger timeTriger;
    @Autowired
    DataSource dataSource;

    @Test
    public void timeTriggerTest() throws IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete from vam_lock_log ");
        timeTriger.timeTrigger();
        System.in.read();
    }
}
