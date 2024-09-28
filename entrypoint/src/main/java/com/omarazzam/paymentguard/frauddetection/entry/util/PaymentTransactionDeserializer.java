package com.omarazzam.paymentguard.frauddetection.entry.util;

import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class PaymentTransactionDeserializer  extends JsonDeserializer<PaymentTransaction> {
}
