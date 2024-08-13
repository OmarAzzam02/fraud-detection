package com.omarazzam.paymentguard.frauddetection.entry.service;


import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Async
@Service
public class SendMessageToEvaluationImpl implements SendMessageToEvaluation {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    @Async
    public void sendMessage(PaymentTransaction message) {
        log.info("Sending Message to Evaluation {}", message.toString());
        jmsTemplate.convertAndSend("messages", message);
    }
}