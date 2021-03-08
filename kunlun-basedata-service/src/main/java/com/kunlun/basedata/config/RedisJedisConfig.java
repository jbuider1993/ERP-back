package com.kunlun.basedata.config;

import com.kunlun.basedata.model.RedisConfigModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisJedisConfig {

    private Logger log = LogManager.getLogger();

    @Autowired
    private RedisConfigModel redisConfig;

    @Bean
    public JedisPool getJedisPool() {
        String host = redisConfig.host;
        int port = redisConfig.port;
        int timeout = redisConfig.timeout;
        JedisPool jedisPool = new JedisPool(getRedisConfig(), host, port, timeout);
        log.info("RedisJedisConfig JedisPool init success: host -> [{}]; port -> [{}]", host, port);
        return jedisPool;
    }

    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.maxActive);
        jedisPoolConfig.setMaxIdle(redisConfig.maxIdle);
        jedisPoolConfig.setMaxWaitMillis(redisConfig.maxWaitMillis);
        log.info("RedisJedisConfig JedisPoolConfig init success");
        return jedisPoolConfig;
    }
}
