package com.kunlun.basedata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
@MapperScan("com.kunlun.basedata")
@EnableSwagger2
@EnableCircuitBreaker
@EnableHystrixDashboard
public class BasedataApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasedataApplication.class, args);
    }
}
