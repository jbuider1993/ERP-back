package com.kunlun.basedata.controller;

import com.kunlun.basedata.service.impl.RedisJedisService;
import com.scmp.common.utils.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    private Logger log = LogManager.getLogger();

    @Autowired
    private RedisJedisService jedisService;

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Object set(String key, String value, int expire, int dataBase) {
        try {
            jedisService.set(key, value, expire, dataBase);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("RedisController hSet Error: ", e);
            return ResponseUtil.failedResponse("缓存到redis失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get(String key, int dataBase) {
        try {
            Object obj = jedisService.get(key, dataBase);
            return ResponseUtil.successResponse(obj);
        } catch (Exception e) {
            log.error("RedisController hGet Error: ", e);
            return ResponseUtil.failedResponse("从redis获取缓存数据失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/hSet", method = RequestMethod.POST)
    public Object hSet(String key, String value, String hash, int expire, int dataBase) {
        try {
            jedisService.hSet(key, value, hash, expire, dataBase);
            return ResponseUtil.successResponse("success");
        } catch (Exception e) {
            log.error("RedisController hSet Error: ", e);
            return ResponseUtil.failedResponse("缓存到redis失败！", e.getMessage());
        }
    }

    @RequestMapping(value = "/hGet", method = RequestMethod.GET)
    public Object hGet(String key, String hash, int dataBase) {
        try {
            Object obj = jedisService.hGet(key, hash, dataBase);
            return ResponseUtil.successResponse(obj);
        } catch (Exception e) {
            log.error("RedisController hGet Error: ", e);
            return ResponseUtil.failedResponse("从redis获取缓存数据失败！", e.getMessage());
        }
    }
}
