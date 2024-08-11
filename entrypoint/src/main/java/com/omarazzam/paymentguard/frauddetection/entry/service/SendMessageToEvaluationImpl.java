package com.omarazzam.paymentguard.frauddetection.entry.service;


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
    public void sendMessage(String message) {
        log.info("Sending Message to Evaluation");

        jmsTemplate.convertAndSend("messages", message);


    }
}
