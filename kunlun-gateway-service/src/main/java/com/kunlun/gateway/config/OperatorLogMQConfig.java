package com.kunlun.gateway.config;

import com.scmp.common.constant.AMQPConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志消息队列配置
 */
@Configuration
public class OperatorLogMQConfig {

    private Logger log = LogManager.getLogger();

    @Bean
    public Queue operatorLogQueue() {
        String queueName = AMQPConstant.OPERATOR_LOG_QUEUE;
        log.info("Operator log queue Created! queueName ===>>> " + queueName);
        return new Queue(queueName, true, false, false, null);
    }

    @Bean
    public FanoutExchange operatorLogFanoutExchange() {
        String exchangeName = AMQPConstant.OPERATOR_LOG_EXCHANGE;
        log.info("Operator log exchange Created! exchangeName ===>>> " + exchangeName);
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public Binding operatorLogBinding() {
        log.info("Operator log queue " + AMQPConstant.OPERATOR_LOG_QUEUE + " binding with exchange " + AMQPConstant.OPERATOR_LOG_EXCHANGE);
        return BindingBuilder.bind(operatorLogQueue()).to(operatorLogFanoutExchange());
    }
}
