package com.vam.task.process;

import com.vam.task.dto.TaskListDto;
import com.vam.task.dto.TaskListResultDto;

/**
 * 任务处理的逻辑
 */
public interface TaskProcessIntf {

    TaskListResultDto process(TaskListDto taskListDto);
}
