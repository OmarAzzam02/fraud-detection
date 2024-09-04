package com.omarazzam.paymentguard.frauddetection.entry.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Async
@Service
public class SendMessageToEvaluationImpl implements SendMessageToEvaluation {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;
    @Override
    @Async
    public void sendMessage(PaymentTransaction message) throws JsonProcessingException {
        log.info("Sending Message to Evaluation {}", message.toString());
        String messageStr = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("transaction", messageStr);

    }
}