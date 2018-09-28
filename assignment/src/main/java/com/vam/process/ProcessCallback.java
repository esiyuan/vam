package com.vam.process;

import com.vam.dto.TaskListResultDto;

/**
 * 任务结果处理
 */
public interface ProcessCallback {


    void handlerResult(TaskListResultDto listResultDto);

}
