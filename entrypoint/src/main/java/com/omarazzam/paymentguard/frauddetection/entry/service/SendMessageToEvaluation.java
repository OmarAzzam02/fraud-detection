package com.omarazzam.paymentguard.frauddetection.entry.service;

import org.springframework.scheduling.annotation.Async;

public interface SendMessageToEvaluation {


     void sendMessage(String message);
}
