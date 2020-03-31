package com.kunlun.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域请求自动配置
 */
@Configuration
@ConditionalOnProperty(name = "scmp.cors.enable", havingValue = "true")
public class CorsAutoConfiguration {

    private Logger log = LogManager.getLogger();

    @Bean
    public CorsFilter corsFilter() {
        log.info("Loading CORS configuration ...");

        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();

        /*是否允许请求带有验证信息*/
        corsConfiguration.setAllowCredentials(true);

        /*允许访问的客户端域名*/
        corsConfiguration.addAllowedOrigin("*");

        /*允许服务端访问的客户端请求头*/
        corsConfiguration.addAllowedHeader("*");

        /*允许访问的方法名,GET POST等*/
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
