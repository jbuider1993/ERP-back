package com.kunlun.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2自动配置类
 */
@Configuration
@ConditionalOnProperty(name = "kunlun.swagger.enable", havingValue = "true")
public class Swagger2AutoConfiguration {

    private Logger log = LogManager.getLogger();

    @Value("${kunlun.swagger.enable}")
    private boolean enable;

    @Value("${kunlun.swagger.basePackage}")
    private String basePackage;

    @Value("${kunlun.swagger.title}")
    private String title;

    @Value("${kunlun.swagger.description}")
    private String description;

    @Value("${kunlun.swagger.version}")
    private String version;

    @Bean
    public Docket configSwagger() {
        log.info("Loading Swagger configuration ...");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description).contact("chenzhiq").version(version).build();
    }
}
