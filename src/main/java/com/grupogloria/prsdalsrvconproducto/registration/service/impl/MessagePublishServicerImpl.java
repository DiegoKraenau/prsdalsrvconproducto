package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        // QueueMessage queueMessage = new QueueMessage();
        // queueMessage.setId(UUID.randomUUID().toString().replace("-", ""));
        // queueMessage.setMessage(message);
        sqsMessageQueueService.sendMessage(queueName, message);
    }

}
