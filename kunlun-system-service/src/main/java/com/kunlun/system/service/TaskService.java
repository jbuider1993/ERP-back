package com.kunlun.system.service;

import com.kunlun.system.utils.LogUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskService {

    private LogUtils log = new LogUtils(TaskService.class);

    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        log.info("---------- start task one ----------");
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        log.info("executor time: " + (endTime - startTime));
        log.info("---------- end task one ----------");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        log.info("---------- start task two ----------");
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        log.info("executor time: " + (endTime - startTime));
        log.info("---------- end task two ----------");
    }
}
