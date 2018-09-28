package com.vam.exec;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExecutorProxy {
    @Autowired
    private SpringBeanExecutor springBeanExecutor;

    public ExecutorIntf getExecutor(String execType) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(execType));
        switch (execType) {
            case "springBeanExecutor":
                return springBeanExecutor;
        }
        return null;
    }

}
