package com.vam.dao;

import com.vam.dmo.ThreadCountDmo;

import java.util.List;

public interface ThreadCountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThreadCountDmo record);

    ThreadCountDmo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThreadCountDmo record);

    int updateByPrimaryKey(ThreadCountDmo record);

    int countByType(String bizType);

    List<ThreadCountDmo> selectThreadCountOfTimeOut(ThreadCountDmo record);

    int batchDeleteByPrimaryKey(List<ThreadCountDmo> threadCountDmos);
}