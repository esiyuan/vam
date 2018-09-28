package com.vam.dao;

import com.vam.dmo.TaskInfoDmo;

import java.util.List;
import java.util.Map;

public interface TaskInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskInfoDmo record);

    /**
     * 批量更新超时任务执行状态
     *
     * @param records
     * @return
     */
    int batchUpdateRunStatusByPrimaryKey(List<TaskInfoDmo> records);

    List<TaskInfoDmo> selectRunningTaskOfTimeOut(TaskInfoDmo record);
    List<TaskInfoDmo> selectWaitingTaskOfTimeComing(TaskInfoDmo record);

    TaskInfoDmo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskInfoDmo record);
    int batchUpdateWaitingTasks(Map<String, Object> record);

    int updateByPrimaryKey(TaskInfoDmo record);
}