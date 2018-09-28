package com.vam.service.impl;

import com.vam.dao.TaskTypeMapper;
import com.vam.dmo.TaskTypeDmo;
import com.vam.service.intf.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {
    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Override
    public List<TaskTypeDmo> selectAll() {
        return taskTypeMapper.selectAll();
    }

    @Override
    public TaskTypeDmo selectByBizType(String bizType) {
        return null;
    }
}
