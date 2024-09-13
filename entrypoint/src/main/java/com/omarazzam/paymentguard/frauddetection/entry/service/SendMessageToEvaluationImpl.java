package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
public class SendMessageToEvaluationImpl implements SendMessageToEvaluation {



    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EvaluatedMessageCashe evaluatedMessageCashe;



    @Override
    public PaymentTransaction sendMessage(final PaymentTransaction message) {
        log.info("Sending Message to Evaluation {}", message.getId());
        kafkaTemplate.send("transaction", message);

        while (!evaluatedMessageCashe.isResponeBack(message.getId())) {}
        PaymentTransaction messageResultBack=evaluatedMessageCashe.getTransaction(message.getId());
        evaluatedMessageCashe.removeTransaction(messageResultBack);

        return messageResultBack;
    }

}
