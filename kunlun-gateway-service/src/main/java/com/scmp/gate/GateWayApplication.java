package com.scmp.gate;

import com.scmp.gate.filter.AuthenticateFilter;
import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients(basePackages = {"com.scmp.gate"})
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
