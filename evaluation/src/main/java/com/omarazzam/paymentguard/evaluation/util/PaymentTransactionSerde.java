package com.omarazzam.paymentguard.evaluation.util;

import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class PaymentTransactionSerde implements Serde<PaymentTransaction> {
    @Override
    public Serializer<PaymentTransaction> serializer() {
        return new PaymentSerializer();
    }

    @Override
    public Deserializer<PaymentTransaction> deserializer() {
        return new PaymentDeserializer();
    }
}
