package com.vam.exec;

import com.vam.dto.TaskListDto;
import com.vam.dto.TaskListResultDto;

public interface ExecutorIntf {

    void process(TaskListDto taskListDto);

}
