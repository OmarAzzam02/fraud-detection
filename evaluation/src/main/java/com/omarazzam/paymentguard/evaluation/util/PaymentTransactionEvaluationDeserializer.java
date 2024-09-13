package com.omarazzam.paymentguard.evaluation.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentTransactionEvaluationDeserializer  implements Deserializer<PaymentTransactionEvaluation> {


    @Autowired
    ObjectMapper objectMapper;



    @Override
    public PaymentTransactionEvaluation deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, PaymentTransactionEvaluation.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing PaymentTransactionEvaluation", e);
        }
    }
}

