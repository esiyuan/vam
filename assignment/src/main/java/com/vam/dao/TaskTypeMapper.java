package com.vam.dao;

import com.vam.dmo.TaskTypeDmo;

import java.util.List;

public interface TaskTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskTypeDmo record);

    TaskTypeDmo selectByPrimaryKey(Long id);

    TaskTypeDmo selectByBizType(String bizType);

    List<TaskTypeDmo> selectAll();

    int updateByPrimaryKeySelective(TaskTypeDmo record);

    int updateByPrimaryKey(TaskTypeDmo record);


}