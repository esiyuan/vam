package com.vam.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ExecutorUtil {

    private static ExecutorService pool =
            Executors.newFixedThreadPool(20);

    public static void execute(Runnable command) {
        pool.execute(command);
    }
}
