package com.omarazzam.paymentguard.frauddetection.entry.util;


import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class PaymentTransactionSererializer extends JsonSerializer<PaymentTransaction> {
}
