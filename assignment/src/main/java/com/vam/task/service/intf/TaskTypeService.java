package com.vam.task.service.intf;

import com.github.pagehelper.Page;
import com.vam.task.dmo.TaskTypeDmo;

import java.util.List;

public interface TaskTypeService {


    List<TaskTypeDmo> selectAll();

    TaskTypeDmo selectByBizType(String bizType);

    Page<TaskTypeDmo> search(TaskTypeDmo taskTypeDmo);

    int insert(TaskTypeDmo record);

}
