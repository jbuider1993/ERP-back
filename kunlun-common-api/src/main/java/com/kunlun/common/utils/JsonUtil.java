package com.kunlun.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtil {

    public static <T> T copyObject(T object, Class<T> clz) {
        String json = JSONObject.toJSONString(object);
        return JSONObject.parseObject(json, clz);
    }

    public static <T> List<T> copyArray(List<?> list, Class<T> clz) {
        String json = JSONObject.toJSONString(list);
        return JSONArray.parseArray(json, clz);
    }
}
