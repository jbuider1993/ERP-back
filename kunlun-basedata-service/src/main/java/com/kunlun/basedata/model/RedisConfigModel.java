package com.kunlun.basedata.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class RedisConfigModel {
    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public int port;

    @Value("${spring.redis.database}")
    public int database;

    @Value("${spring.redis.timeout}")
    public int timeout;

    @Value("${spring.redis.jedis.pool.min-idle}")
    public int minIdle;

    @Value("${spring.redis.jedis.pool.max-idle}")
    public int maxIdle;

    @Value("${spring.redis.jedis.pool.max-active}")
    public int maxActive;

    @Value("${spring.redis.jedis.pool.max-wait}")
    public long maxWaitMillis;
}
