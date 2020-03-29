package com.scmp.system.amqp;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.scmp.common.constant.AMQPConstant;
import com.scmp.common.model.OperatorLogModel;
import com.scmp.system.service.impl.OperateLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 操作日志接收器
 */
@Component
public class OperateLogReceiver {

    private Logger log = LogManager.getLogger();

    @Autowired
    private OperateLogService operateLogService;

    @RabbitListener(queues = AMQPConstant.OPERATOR_LOG_QUEUE)
    public void receiveOperatorLog(String messages, Channel channel, Message message) {
        try {
            log.info("Receive operator log queue ===>>> " + AMQPConstant.OPERATOR_LOG_QUEUE + ", routingKey ===>>> " + message.getMessageProperties().getReceivedRoutingKey());

            // 接收日志信息，进行消费端确认
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicAck(deliveryTag, false);
            log.info("Operator log received! messages ===>>> " + messages);

            // 保存/更新操作日志
            OperatorLogModel logModel = JSONObject.parseObject(messages, OperatorLogModel.class);
            if (ObjectUtils.isEmpty(logModel.getExceptionInfo())) {
                operateLogService.insertOperateLog(logModel);
            } else {
                operateLogService.updateOperateLog(logModel);
            }

            // 关闭通道
            if (channel != null) {
                channel.abort();
            }
        } catch (Exception e) {
            log.error("ReceiveOperateLog receiveOperatorLog Error: ", e);
        }
    }
}
