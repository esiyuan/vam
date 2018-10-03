package com.vam.task.exec;

import com.vam.task.dto.TaskListDto;

public interface ExecutorIntf {

    void process(TaskListDto taskListDto);

}
