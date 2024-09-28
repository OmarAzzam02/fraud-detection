package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class SendMessageToEvaluationImpl implements SendMessageToEvaluation {




    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EvaluatedMessageCashe evaluatedMessageCashe;

    @Autowired
    MonitorService monitorService;

    @Override
    public PaymentTransaction sendMessage(final PaymentTransaction message) {
        log.info("Sending Message to Evaluation {}", message.getId());

        kafkaTemplate.send("transaction", message);

        synchronized (monitorService.getSharedMonitor()) {
            while (!evaluatedMessageCashe.isResponeBack(message.getId())) {
                try {
                    monitorService.getSharedMonitor().wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            PaymentTransaction messageResultBack = evaluatedMessageCashe.getEvaluatedMessages().get(message.getId());
            evaluatedMessageCashe.removeTransaction(messageResultBack);

            log.info("Message Result Back in reader {}", messageResultBack.getId());

            return messageResultBack;
        }
    }

    @KafkaListener(topics = "evaluated-transactions", groupId = "evaluated-transactions")
    public void recieveEvaluatedMessage(PaymentTransaction evaluatedPaymentTransaction, Acknowledgment ack) {
        try {
            log.info("Received evaluated message: {}", evaluatedPaymentTransaction.getId());

            evaluatedMessageCashe.addTransaction(evaluatedPaymentTransaction);

            synchronized (monitorService.getSharedMonitor()) {
                ack.acknowledge();
                monitorService.getSharedMonitor().notifyAll();
            }

        } catch (final Exception e) {
            log.error("Error processing message", e);
        }
    }
}
