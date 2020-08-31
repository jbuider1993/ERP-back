package com.kunlun.basedata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.basedata.model.vo.RedisInfoVo;
import com.kunlun.basedata.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("redisService")
public class RedisJedisService implements IRedisService {

    private final static int DEFAULT_EXISTS = 1000 * 60 * 30;

    private final static int DEFAULT_DATABASE = 0;

    private final static String DEFAULT_HASH = "kunlun";

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    @Override
    public void set(String key, String value, int expire, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            jedis.set(key, value);
            jedis.expire(key, expire == 0 ? DEFAULT_EXISTS : expire);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Object get(String key, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Object del(String key, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Object keys(String pattern, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.keys(pattern);
        } finally {
            jedis.close();
        }
    }

    @Override
    public void hSet(String key, String value, String hash, int expire, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            jedis.hset(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, key, value);
            jedis.expire(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, expire == 0 ? DEFAULT_EXISTS : expire);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Object hGet(String key, String hash, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.hget(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Object hdel(String key, String hash, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.hdel(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash, key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<String> hKeys(String hash, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.hkeys(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Map<String, String> getAllKeys(String hash, int dataBase) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase == 0 ? DEFAULT_DATABASE : dataBase);
            return jedis.hgetAll(StringUtils.isEmpty(hash) ? DEFAULT_HASH : hash);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<RedisInfoVo> getRedisInfo() throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Client client = jedis.getClient();
            client.info();
            String info = client.getBulkReply();

            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = dateTime.format(formatter);

            RedisInfoVo redisMemory = new RedisInfoVo();
            redisMemory.setTime(time);
            redisMemory.setType("memory");
            String memory = info.substring(info.indexOf("used_memory") + 12, info.indexOf("used_memory_human")).trim();
            long memorySize = Long.parseLong(memory);
            BigDecimal bigDecimal = new BigDecimal(memorySize);
            BigDecimal result = bigDecimal.divide(new BigDecimal(1024)).setScale(2, BigDecimal.ROUND_HALF_UP);
            redisMemory.setValue(result.toString());

            int keyNum = 0;
            for (int i = 0; i < 16; i++) {
                String key = "db" + i + ":keys=";
                if (info.indexOf(key) > -1) {
                    info = info.substring(info.indexOf(key));
                    String num = info.substring(info.indexOf("=") + 1, info.indexOf(","));
                    keyNum += Integer.parseInt(num);
                }
            }

            RedisInfoVo redisKeys = new RedisInfoVo();
            redisKeys.setTime(time);
            redisKeys.setValue(String.valueOf(keyNum));
            redisKeys.setType("keyValue");

            List<RedisInfoVo> redisInfoVos = new ArrayList<>();
            redisInfoVos.add(redisMemory);
            redisInfoVos.add(redisKeys);
            return redisInfoVos;
        } finally {
            jedis.close();
        }
    }
}
