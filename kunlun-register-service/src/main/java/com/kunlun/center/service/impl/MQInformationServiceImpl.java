package com.kunlun.center.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kunlun.center.model.MQInfoVo;
import com.kunlun.center.service.IMQInformationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    public List<MQInfoVo> getMessages() throws Exception {
        String message = getMessageFromMQ();
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject messageObject = jsonObject.getJSONObject("queue_totals");
        JSONObject queueObject = jsonObject.getJSONObject("object_totals");

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = dateTime.format(formatter);

        List<MQInfoVo> mqList = new ArrayList<>();
        MQInfoVo messageVo = new MQInfoVo();
        messageVo.setTime(time);
        messageVo.setType("message");
        messageVo.setValue(messageObject.getInteger("messages"));
        mqList.add(messageVo);

        MQInfoVo exchangeVo = new MQInfoVo();
        exchangeVo.setTime(time);
        exchangeVo.setType("exchange");
        exchangeVo.setValue(queueObject.getInteger("exchanges"));
        mqList.add(exchangeVo);

        MQInfoVo queueVo = new MQInfoVo();
        queueVo.setTime(time);
        queueVo.setType("queue");
        queueVo.setValue(queueObject.getInteger("queues"));
        mqList.add(queueVo);

        MQInfoVo channelVo = new MQInfoVo();
        channelVo.setTime(time);
        channelVo.setType("channel");
        channelVo.setValue(queueObject.getInteger("channels"));
        mqList.add(channelVo);
        return mqList;
    }

    public String getMessageFromMQ() {
        String requestUrl = "http://" + host + ":15672/api/overview";
        HttpURLConnection httpConnection = null;
        try {
            URL url = new URL(requestUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
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
                log.info("not get connection from RabbitMQ");
                return null;
            }
        } catch (IOException e) {
            log.error("get information from RabbitMQ error: ", e);
            return null;
        } finally {
            httpConnection.disconnect();
        }
    }
}
