package com.omarazzam.paymentguard.evaluation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.StopWatch;


@Log4j2
@Service
public class ReceiveMessage {

    @Autowired
    EvaluateMessageService evaluateMeesageService;

    @Autowired
    ObjectMapper objectMapper;


    @KafkaListener(topics = "transaction", groupId = "evaluation-service")
    public void receiveMessage(ConsumerRecord<String, String> record , Acknowledgment acknowledgment) {
        PaymentTransactionEvaluation message = null;
        try {
        message = objectMapper.readValue(record.value(), PaymentTransactionEvaluation.class);
        log.info("Offset {} " ,record.offset());
        log.info("Received message from KAFKA : {}", message.toString());
        } catch (Exception e) {
            log.error(e);
        }


        long startTime = System.currentTimeMillis();
        evaluateMeesageService.evaluateMessage(message);
        long endTime = System.currentTimeMillis();
        log.info("Time elapsed : {} ms", (endTime - startTime));
        acknowledgment.acknowledge();
    }

}
