package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Log4j2
@Service
public class SendMessageToEvaluationImpl implements SendMessageToEvaluation {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EvaluatedMessageCashe evaluatedMessageCashe;

    @Override
    public PaymentTransaction sendMessage(final PaymentTransaction message) {
     //   log.info("Sending Message to Evaluation {}", message.getId());


          kafkaTemplate.send("transaction", message);


        while (!evaluatedMessageCashe.isResponeBack(message.getId())) {}
        PaymentTransaction messageResultBack = evaluatedMessageCashe.getEvaluatedMessages().get(message.getId());
        evaluatedMessageCashe.removeTransaction(messageResultBack);

    log.info(" message Result Back in reader ");

        return messageResultBack;
    }











}




