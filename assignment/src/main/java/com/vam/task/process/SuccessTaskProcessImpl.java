package com.vam.task.process;

import com.vam.task.dto.ProcessStatusEnum;
import com.vam.task.dto.TaskInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("successTaskProcessImpl")
public class SuccessTaskProcessImpl extends AbstraceTaskProcessor {

    @Override
    protected ProcessStatusEnum execTask(TaskInfoDto taskInfoDto) {
        log.info("taskInfoDto = {}", taskInfoDto);
        return ProcessStatusEnum.SUCCESS;
    }
}
