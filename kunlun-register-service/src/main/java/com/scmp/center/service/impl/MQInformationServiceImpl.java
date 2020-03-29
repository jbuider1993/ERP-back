package com.scmp.center.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scmp.center.service.IMQInformationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MQInformationServiceImpl implements IMQInformationService {

    private Logger log = LogManager.getLogger();

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Override
    public Map<String, JSONArray> getMessages() throws Exception {
        // 获取MQ队列
        String queues = getMessages("queues");
        JSONArray queueArray = JSON.parseArray(queues);

        // 获取MQ交换器
        String exchanges = getMessages("exchanges");
        JSONArray exchangeArray = JSON.parseArray(exchanges);

        Map<String, JSONArray> resultMap = new HashMap<>();
        resultMap.put("queues", queueArray);
        resultMap.put("exchanges", queueArray);
        return resultMap;
    }

    public String getMessages(String typeName) {
        String requestUrl = "http://" + host + ":15672/api/" + typeName;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            String auth = username + ":" + password;

            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(auth.getBytes());

            httpConnection.setDoOutput(true);
            httpConnection.setRequestProperty("Authorization", "Basic " + encode);
            httpConnection.connect();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder stringBuilder = new StringBuilder();
                String strTemp = "";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                while ((strTemp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strTemp);
                }
                bufferedReader.close();
                httpConnection.disconnect();
                return stringBuilder.toString();
            } else {
                httpConnection.disconnect();
                return null;
            }
        } catch (Exception e) {
            log.error("MQInformationServiceImpl getMessages Error: ", e);
            return null;
        }
    }
}
