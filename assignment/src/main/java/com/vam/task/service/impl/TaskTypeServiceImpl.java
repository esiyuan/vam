package com.vam.task.service.impl;

import com.github.pagehelper.Page;
import com.vam.task.dao.TaskTypeMapper;
import com.vam.task.dmo.TaskTypeDmo;
import com.vam.task.service.intf.TaskTypeService;
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

    @Override
    public Page<TaskTypeDmo> search(TaskTypeDmo taskTypeDmo) {
        return null;
    }

    @Override
    public int insert(TaskTypeDmo record) {
        return taskTypeMapper.insert(record);
    }
}
