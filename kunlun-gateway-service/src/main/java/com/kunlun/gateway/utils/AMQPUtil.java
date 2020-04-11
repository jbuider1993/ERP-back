package com.kunlun.gateway.utils;

import com.kunlun.common.constant.AMQPConstant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.kunlun.gateway.model.MQConfigModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class AMQPUtil {

    private Logger log = LogManager.getLogger();

    @Autowired
    private MQConfigModel mqConfigModel;

    /**
     * 生成消息队列链接
     */
    public Connection generateConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            factory.setHost(mqConfigModel.getHost());
            factory.setPort(Integer.parseInt(mqConfigModel.getPort()));
            factory.setUsername(mqConfigModel.getUsername());
            factory.setPassword(mqConfigModel.getPassword());
            connection = factory.newConnection();
            log.info("Generate RabbitMQ Connection");
        } catch (IOException e) {
            log.error("AMQPUtil generateConnection IOException: ", e);
        } catch (TimeoutException e) {
            log.error("AMQPUtil generateConnection TimeoutException: ", e);
        }
        return connection;
    }

    /**
     * 设置死信消息队列
     */
    public void setDeadQueue(Channel channel) throws Exception {
        String deadExchange = AMQPConstant.DEAD_EXCHANGE;
        String deadQueue = AMQPConstant.DEAD_QUEUE;
        channel.exchangeDeclare(deadExchange, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(deadQueue, false, false, false, null);
        channel.queueBind(deadQueue, deadExchange, AMQPConstant.DEAD_ROUTING_KEY);
        log.info("Declare dead queue " + deadQueue + " exchange " + deadExchange);
    }
}
