package com.kunlun.system.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * Spring定时任务用法
 *
 * 1、所有的定时任务都是在同一个线程池运行
 * 2、每个定时任务都启动一个线程
 * 3、如要线程池或多线程处理spring的定时任务，有两种方法：
 * 一是实现SchedulingConfigurer接口，设置scheduler属性为固定数量的线程池；
 * 二是在启动类加@Async注解，然后在spring定时任务上添加@Async
 */
@Configuration
public class ScheduleTaskConfig implements SchedulingConfigurer {

    private Logger log = LogManager.getLogger();

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
        log.info("===== 设置Spring定时任务多线程运行 =====");
    }
}
