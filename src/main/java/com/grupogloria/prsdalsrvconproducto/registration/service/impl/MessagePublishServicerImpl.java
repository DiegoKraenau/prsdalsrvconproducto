package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.io.IOException;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.grupogloria.prsdalsrvconproducto.registration.domain.QueueMessage;

@Component
@Slf4j
public class MessagePublishServicerImpl {

    @Value("${aws.sqs.queueName}")
    private String queueName;
    
    private final SqsMessageQueueService sqsMessageQueueService;
    
    public MessagePublishServicerImpl(final SqsMessageQueueService sqsMessageQueueService) {
        this.sqsMessageQueueService = sqsMessageQueueService;
    }

    public void send(String message) {
        //QueueMessage queueMessage = new QueueMessage();
        //queueMessage.setId(UUID.randomUUID().toString().replace("-", ""));
        //queueMessage.setMessage(message);
        sqsMessageQueueService.sendMessage(queueName, message);
    }
	
	
}
