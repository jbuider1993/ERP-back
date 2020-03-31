package com.kunlun.gateway.amqp;

import com.kunlun.gateway.utils.AMQPUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;
import com.scmp.common.constant.AMQPConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * 操作日志发送器
 */
@Component
public class OperatorLogSender {
    private Logger log = LogManager.getLogger();

    @Autowired
    private AMQPUtil amqpUtil;

    public void sendOperatorLog(String messages) {
        try {
            // 获取MQ链接及通道
            Connection connection = amqpUtil.generateConnection();
            Channel channel = connection.createChannel();

            // 设置死信队列
            amqpUtil.setDeadQueue(channel);

            // 日志消息发送
            AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder().contentEncoding("UTF-8").contentType("text/plain").headers(new HashMap<String, Object>()).priority(0).expiration(30000 + "");
            channel.basicPublish(AMQPConstant.OPERATOR_LOG_EXCHANGE, AMQPConstant.OPERATOR_LOG_ROUTING_KEY, true, properties.build(), messages.getBytes("UTF-8"));
            log.info("Operator log sended! messages ===>>> " + messages);

            // 消息确认机制-confirm模式
            channel.confirmSelect();

            // 同步确认，等待服务端回复后才算确认
            if (!channel.waitForConfirms()){
                log.info("Operator log sended to server FAILURE");
            } else {
                log.info("Operator log sended to server SUCCESS");
            }

            // 异步消息确认
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    log.info("SUCCESS! Ack: deliveryTag ===>>> " + deliveryTag + "， multiple ===>>> " + multiple);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    log.info("FAILURE! NO Ack: deliveryTag ===>>> " + deliveryTag + "， multiple ===>>> " + multiple);
                }
            });

            // 当Exchange不存在或RoutingKey路由不到时，即监听不可达的消息
            // mandatory 设置为true 路由不可达的消息会被监听到，不会被自动删除(即basicPublish方法中的第三个参数)
            channel.addReturnListener(new ReturnListener() {
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    log.info("Return message relyCode ===>>> " + replyCode + ", replyText ===>>> " + replyText + ", routingKey ===>>> " + routingKey);
                }
            });
        } catch (Exception e) {
            log.error("OperatorLogSender sendOperatorLog Error: ", e);
        }
    }
}
