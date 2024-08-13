package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.springframework.scheduling.annotation.Async;

public interface SendMessageToEvaluation {


     void sendMessage(PaymentTransaction message);
}
