//package com.omarazzam.paymentguard.evaluation.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
//import lombok.extern.log4j.Log4j2;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//
//
//@Log4j2
//@Service
//public class ReceiveMessage {
//
//    @Autowired
//    EvaluateMessageService evaluateMeesageService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//
//    @KafkaListener(topics = "transaction", groupId = "evaluation-service")
//    public void receiveMessage( PaymentTransactionEvaluation message, Acknowledgment acknowledgment) {
//
//
//        try {
//
//            log.info("Received message from KAFKA : {}", message.toString());
//        } catch (final Exception e) {
//            log.error(e);
//        }
//
//
//        long startTime = System.currentTimeMillis();
//        String result = evaluateMeesageService.evaluateMessage(message);
//        long endTime = System.currentTimeMillis();
//        log.info("Time elapsed : {} ms", (endTime - startTime));
//
//
//        // send back response
//
//    acknowledgment.acknowledge();
//
//
//
//    }
//
//}
