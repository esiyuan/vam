package com.vam.service.intf;

import com.vam.dmo.TaskTypeDmo;

import java.util.List;

public interface TaskTypeService {


    List<TaskTypeDmo> selectAll();

    TaskTypeDmo selectByBizType(String bizType);
}
