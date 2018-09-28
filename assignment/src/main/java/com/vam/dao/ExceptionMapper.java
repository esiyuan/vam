package com.vam.dao;

import com.vam.dmo.ExceptionDmo;

public interface ExceptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExceptionDmo record);

    ExceptionDmo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExceptionDmo record);

    int updateByPrimaryKey(ExceptionDmo record);
}