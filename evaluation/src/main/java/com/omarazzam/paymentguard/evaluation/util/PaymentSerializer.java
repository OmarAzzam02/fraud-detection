package com.omarazzam.paymentguard.evaluation.util;

import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class PaymentSerializer extends JsonSerializer<PaymentTransaction> {
}
