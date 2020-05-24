package com.scmp.base.service;

import java.util.Map;
import java.util.Set;

public interface IRedisService {
    public void set(String key, String value, int expire, int dataBase) throws Exception;

    public Object get(String key, int dataBase) throws Exception;

    public Object del(String key, int dataBase) throws Exception;

    public void hSet(String key, String value, String hash, int expire, int dataBase) throws Exception;

    public Object hGet(String key, String hash, int dataBase) throws Exception;

    public Object hdel(String key, String hash, int dataBase) throws Exception;

    public Set<String> hKeys(String hash, int dataBase) throws Exception;

    public Map<String, String> getAllKeys(String hash, int dataBase) throws Exception;
}