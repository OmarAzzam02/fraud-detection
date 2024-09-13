package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface SendMessageToEvaluation {
    PaymentTransaction sendMessage(PaymentTransaction message);
}
