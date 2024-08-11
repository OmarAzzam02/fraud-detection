package com.omarazzam.paymentguard.evaluation.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ReceiveMessage {


    @JmsListener(destination = "messages")
    public void receiveMessage(String message) {
        log.info("Received message from activeMQ : " + message);
    }

}
