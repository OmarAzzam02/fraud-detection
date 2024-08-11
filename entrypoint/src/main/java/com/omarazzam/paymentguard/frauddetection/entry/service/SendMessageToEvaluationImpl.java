package com.omarazzam.paymentguard.frauddetection.entry.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Async
@Service
public class SendMessageToEvaluationImpl implements SendMessageToEvaluation {

    @Override
    @Async
    public void sendMessage(String message) {
        log.info("Sending Message to Evaluation");

    }
}
