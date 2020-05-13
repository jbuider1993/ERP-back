package com.kunlun.gateway;

import com.kunlun.gateway.filter.AuthenticateFilter;
import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableSwagger2
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
@EnableFeignClients(basePackages = {"com.kunlun.gateway"})
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

    @Bean
    Retryer feignRetryer() {
        return new Retryer.Default();
    }

    @Bean
    public AuthenticateFilter authFilter() {
        return new AuthenticateFilter();
    }
}
