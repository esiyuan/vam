package com.vam.exec;

import com.vam.dto.TaskListDto;
import com.vam.dto.TaskListResultDto;
import com.vam.process.ProcessCallback;
import com.vam.process.TaskProcessIntf;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springBeanExecutor")
public class SpringBeanExecutor implements ExecutorIntf, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Autowired
    private ProcessCallback processCallback;

    @Override
    public void process(TaskListDto taskListDto) {
        TaskProcessIntf taskProcess = applicationContext.getBean(taskListDto.getProcessorName(), TaskProcessIntf.class);
        processCallback.handlerResult(taskProcess.process(taskListDto));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
