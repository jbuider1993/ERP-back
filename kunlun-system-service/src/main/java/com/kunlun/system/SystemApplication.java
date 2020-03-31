package com.kunlun.system;

import com.kunlun.system.service.TaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

/**
 * SCMP系统应用
 */
@EnableEurekaClient
@SpringBootApplication(
        exclude = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.activiti.spring.boot.SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class,
                ManagementWebSecurityAutoConfiguration.class
        })
@EnableCaching
@EnableSwagger2
@MapperScan("com.scmp.system")
public class SystemApplication {
    @Autowired
    private TaskService taskService;

    private static Logger log = LoggerFactory.getLogger(SystemApplication.class);

    public static void main( String[] args ) {
        log.info("BootApplication start");
        SpringApplication.run(SystemApplication.class, args);
        log.info("BootApplication start success");
    }

    @PostConstruct
    public void init() {
        log.info("========start========");
        try {
            taskService.doTaskOne();
            taskService.doTaskTwo();
        }
        catch (Exception e) {
            log.error("error", e);
        }
        log.info("========end========");
    }
}
