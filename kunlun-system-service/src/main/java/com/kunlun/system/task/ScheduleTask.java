package com.kunlun.system.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    private Logger log = LogManager.getLogger();

    // Spring定时任务用法（单线程）：
    // 1、使用 @Scheduled来创建定时任务 这个注解用来标注一个定时任务方法，所有的定时任务都是在同一个线程池用同一个线程来处理的
    // 2、@Scheduled源码可以看出它支持多种参数：
    // （1）cron：cron表达式，指定任务在特定时间执行；
    // （2）fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms；
    // （3）fixedDelayString：与fixedDelay含义一样，只是参数类型变为String；
    // （4）fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms；
    // （5）fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String；
    // （6）initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms；
    // （7）initialDelayString：与initialDelay的含义一样，只是将参数类型变为String；
    // （8）zone：时区，默认为当前时区，一般没有用到。

    // 按cron规则执行
    @Scheduled(cron = "0/30 * * * * *")
//    @Async
    public void runScheduleTaskA() {
        log.info("ScheduleTaskA run ---> cron = \"0/30 * * * * *\"");
    }

    // 第一次延迟1秒执行，然后在上一次执行完毕时间点后2秒再次执行
    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
//    @Async
    public void runScheduleTaskB() {
        log.info("ScheduleTaskB run ---> initialDelay = 5000, fixedDelay = 5000");
    }

    // 上一次开始执行时间点后2秒再次执行
    @Scheduled(fixedRate = 5000)
//    @Async
    public void runScheduleTaskC() {
        log.info("ScheduleTaskC run ---> fixedRate = 5000");
    }

    // 上一次执行完毕时间点后2秒再次执行
    @Scheduled(fixedDelay = 5000)
//    @Async
    public void runScheduleTaskD() {
        log.info("ScheduleTaskD run ---> fixedDelay = 5000");
    }
}
