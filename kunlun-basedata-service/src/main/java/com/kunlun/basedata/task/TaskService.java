package com.kunlun.basedata.task;

import com.kunlun.basedata.service.IOnlineUserService;
import com.kunlun.basedata.service.IRedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TaskService {

    private static Logger log = LogManager.getLogger();

    @Autowired
    @Qualifier("redisService")
    private IRedisService redisService;

    @Autowired
    private IOnlineUserService onlineUserService;

    public void prepareTask() {
        log.info("TaskService prepareTask start");
        ScheduledExecutorService executorPool = Executors.newScheduledThreadPool(1);
        ScheduleCacheTask task = new ScheduleCacheTask(redisService, onlineUserService);
        executorPool.scheduleAtFixedRate(task, 1, 60 * 35, TimeUnit.SECONDS);
        log.info("TaskService prepareTask end");
    }
}
