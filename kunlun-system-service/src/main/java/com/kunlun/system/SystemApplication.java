package com.kunlun.system;

import com.kunlun.common.utils.SchemaUtil;
import com.kunlun.system.task.ScheduleTaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableCircuitBreaker
@EnableTurbine
@MapperScan("com.kunlun.system")
public class SystemApplication implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(SystemApplication.class);

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    public static void main( String[] args ) {
        // 初始化数据库
        SchemaUtil.checkAndInitSchema("kunlun_system", "系统业务数据数据库");
        SchemaUtil.checkAndInitSchema("kunlun_activiti", "系统Activiti流程数据库");

        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Override
    public void run(String... args) throws Exception {
        scheduleTaskService.startMonitor();
    }
}
