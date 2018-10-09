package com.vam.task.service.intf;

import com.vam.task.dmo.TaskInfoDmo;
import com.vam.task.dmo.TaskTypeDmo;

public interface TaskInfoService {
    /**
     * 处理超时的任务
     * <p>改变任务状态  从 执行中 -->  待执行
     * <p>触发任务重复执行机制
     */
    void processTaskTimeout();

    int deleteById(Long id);

    /**
     * 按照任务类型进行任务分派
     * @param taskTypeDmo
     */
    void assignTask(TaskTypeDmo taskTypeDmo);

    boolean dealTaskMove(TaskTypeDmo taskTypeDmo, TaskInfoDmo taskInfoDmo);

    void moveToAnotherType(TaskInfoDmo taskInfoDmo, TaskTypeDmo taskTypeDmo);

    TaskInfoDmo selectByPrimaryKey(Long id);
}
