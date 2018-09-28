package com.vam.process;

import com.vam.dto.TaskListDto;
import com.vam.dto.TaskListResultDto;

/**
 * 任务处理的逻辑
 */
public interface TaskProcessIntf {

    TaskListResultDto process(TaskListDto taskListDto);
}
