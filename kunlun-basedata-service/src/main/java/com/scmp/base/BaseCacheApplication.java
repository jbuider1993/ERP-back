package com.scmp.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
@MapperScan("com.scmp.base")
@EnableSwagger2
@EnableCircuitBreaker
public class BaseCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseCacheApplication.class, args);
    }
}
