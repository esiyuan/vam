package com.vam.task.dao;

import com.github.pagehelper.Page;
import com.vam.task.dmo.ExceptionDmo;
import com.vam.task.dmo.TaskInfoDmo;

import java.util.Map;

public interface ExceptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExceptionDmo record);

    ExceptionDmo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExceptionDmo record);

    int updateByPrimaryKey(ExceptionDmo record);

    Page<ExceptionDmo> search(Map<String, Object> taskInfoMap);
}