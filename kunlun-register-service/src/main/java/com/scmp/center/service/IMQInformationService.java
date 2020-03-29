package com.scmp.center.service;

import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public interface IMQInformationService {
    public Map<String, JSONArray> getMessages() throws Exception;
}
