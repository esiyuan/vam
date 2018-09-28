package com.vam.process;

import com.vam.dto.ProcessStatusEnum;
import com.vam.dto.TaskInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("simpleTaskProcess")
public class SimpleTaskProcessImpl extends AbstraceTaskProcessor {

    @Override
    protected ProcessStatusEnum execTask(TaskInfoDto taskInfoDto) {
        log.info("taskInfoDto = {}", taskInfoDto);
        return ProcessStatusEnum.SUCCESS;
    }
}
