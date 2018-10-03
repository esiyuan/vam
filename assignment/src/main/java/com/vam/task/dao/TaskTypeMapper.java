package com.vam.task.dao;

import com.github.pagehelper.Page;
import com.vam.task.dmo.TaskTypeDmo;

import java.util.List;

public interface TaskTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskTypeDmo record);

    TaskTypeDmo selectByPrimaryKey(Long id);

    TaskTypeDmo selectByBizType(String bizType);

    List<TaskTypeDmo> selectAll();

    int updateByPrimaryKeySelective(TaskTypeDmo record);

    int updateByPrimaryKey(TaskTypeDmo record);

    public Page<TaskTypeDmo> search(TaskTypeDmo taskTypeDmo);


}