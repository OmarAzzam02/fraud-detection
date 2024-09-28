package com.omarazzam.paymentguard.evaluation.util;

import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class PaymentDeserializer extends JsonDeserializer<PaymentTransaction> {


}
