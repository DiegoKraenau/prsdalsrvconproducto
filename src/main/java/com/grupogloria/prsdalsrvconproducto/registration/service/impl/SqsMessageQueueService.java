package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.grupogloria.prsdalsrvconproducto.registration.domain.QueueMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SqsMessageQueueService {

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SqsMessageQueueService(@Qualifier("amazonSQSAsync") final AmazonSQSAsync amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQS);
    }

    public void sendMessage(String queueName, String queueMessage) {
    	String id = UUID.randomUUID().toString().replace("-", "");
        Map<String, Object> headers = new HashMap<>();
        headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, UUID.randomUUID().toString().replace("-", ""));
        headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, id.concat("-").concat(UUID.randomUUID().toString().replace("-", "")));
        queueMessagingTemplate.convertAndSend(queueName, queueMessage, headers);
    }
}
