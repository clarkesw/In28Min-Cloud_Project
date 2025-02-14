package com.micro.exchange.service;

import com.micro.exchange.beans.LogMessage;
import com.micro.exchange.config.RabbitMQConfig;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoggingService implements Serializable{

    private final RabbitTemplate rabbitTemplate;

    public LoggingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendLog(String level, String message) {
        LogMessage logMessage = new LogMessage(level, message, LocalDateTime.now().toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_QUEUE, logMessage);
    }

    public void info(String message) {
        LogMessage logMessage = new LogMessage("INFO", message, LocalDateTime.now().toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_QUEUE, logMessage);
    }

    public void debug(String message) {
        LogMessage logMessage = new LogMessage("DEBUG", message, LocalDateTime.now().toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.LOG_QUEUE, logMessage);
    }
}
