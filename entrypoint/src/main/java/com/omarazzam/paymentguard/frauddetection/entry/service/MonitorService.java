package com.omarazzam.paymentguard.frauddetection.entry.service;


import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MonitorService {
    private final  PaymentTransaction sharedMonitor = new PaymentTransaction();
}
