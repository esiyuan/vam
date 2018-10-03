package com.vam.task.process;

import com.vam.task.dto.TaskListResultDto;

/**
 * 任务结果处理
 */
public interface ProcessCallback {


    void handlerResult(TaskListResultDto listResultDto);

}
