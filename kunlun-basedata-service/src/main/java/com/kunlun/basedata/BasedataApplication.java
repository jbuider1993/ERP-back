package com.kunlun.basedata;

import com.kunlun.basedata.task.ScheduleTaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
@MapperScan("com.kunlun.basedata")
@EnableSwagger2
@EnableCircuitBreaker
@EnableTurbine
public class BasedataApplication implements CommandLineRunner {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    public static void main(String[] args) {
        SpringApplication.run(BasedataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 定时删除缓存的Token
        scheduleTaskService.prepareCache();
    }
}
