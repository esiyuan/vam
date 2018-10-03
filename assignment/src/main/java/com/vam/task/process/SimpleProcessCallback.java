package com.vam.task.process;

import com.vam.task.dto.TaskListResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleProcessCallback implements ProcessCallback {

    @Override
    public void handlerResult(TaskListResultDto listResultDto) {
        log.info("任务处理完成:{}", listResultDto);
    }
}
