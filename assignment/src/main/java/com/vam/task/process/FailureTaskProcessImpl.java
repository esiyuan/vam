package com.vam.task.process;

import com.vam.task.dto.ProcessStatusEnum;
import com.vam.task.dto.TaskInfoDto;
import org.springframework.stereotype.Component;

@Component("failureTaskProcessImpl")
public class FailureTaskProcessImpl extends AbstraceTaskProcessor {

    @Override
    protected ProcessStatusEnum execTask(TaskInfoDto taskInfoDto) {
        return ProcessStatusEnum.FAIL;
    }
}
