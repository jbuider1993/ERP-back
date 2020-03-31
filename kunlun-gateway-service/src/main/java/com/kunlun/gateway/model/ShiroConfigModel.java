package com.kunlun.gateway.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Apache Shiro配置模型
 */
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfigModel {
    /**
     * 密钥
     */
    private String secret;

    /**
     * 过期时长
     */
    private Integer expireTime;

    /**
     * header中Token变量名
     */
    private String tokenHeader;

    /**
     * ZuulFilter不过滤的url请求
     */
    private String ignoreUrls;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(String ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }
}
