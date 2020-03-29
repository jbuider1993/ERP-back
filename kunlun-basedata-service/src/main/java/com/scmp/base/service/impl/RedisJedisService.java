package com.scmp.base.service.impl;

import com.scmp.base.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

@Service
public class RedisJedisService implements IRedisService {

    private final static int DEFAULT_EXISTS = 1000 * 60 * 30;

    private final static int DEFAULT_DATABASE = 0;

    private final static String DEFAULT_HASH = "scmp";

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    @Override
    public void set(String key, String value, int expire, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        jedis.set(key, value);
        jedis.expire(key, expire == 0 ? DEFAULT_EXISTS : expire);
        jedis.close();
    }

    @Override
    public Object get(String key, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        jedis.close();
        return jedis.get(key);
    }

    @Override
    public Object del(String key, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        jedis.close();
        return jedis.del(key);
    }

    @Override
    public void hSet(String key, String value, String hash, int expire, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        jedis.hset(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, key, value);
        jedis.expire(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, expire == 0 ? DEFAULT_EXISTS : expire);
        jedis.close();
    }

    @Override
    public Object hGet(String key, String hash, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        jedis.close();
        return jedis.hget(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, key);
    }

    @Override
    public Object hdel(String key, String hash, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        jedis.close();
        return jedis.hdel(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, key);
    }

    @Override
    public Set<String> hKeys(String hash, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        return jedis.hkeys(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash);
    }

    @Override
    public Map<String, String> getAllKeys(String hash, int dataBase) throws Exception {
        Jedis jedis  = getJedis();
        jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
        return jedis.hgetAll(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash);
    }
}
