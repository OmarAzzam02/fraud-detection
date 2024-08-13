package com.omarazzam.paymentguard.evaluation.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.entity.PaymentTransactionEvaluation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ReceiveMessage {

    @Autowired
    ObjectMapper objectMapper;

    @JmsListener(destination = "messages")
    public void receiveMessage(PaymentTransactionEvaluation message) {
        try {
     //   PaymentTransactionEvaluation message = objectMapper.readValue(jsonMessage, PaymentTransactionEvaluation.class);
        log.info("Received message from activeMQ : " + message.toString());
        }catch (Exception e){
            log.error(e);
        }

    }

}
