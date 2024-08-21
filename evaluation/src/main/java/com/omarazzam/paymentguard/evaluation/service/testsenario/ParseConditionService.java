package com.omarazzam.paymentguard.evaluation.service.testsenario;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.evaluation.entity.senario.ConditionDetails;
import com.omarazzam.paymentguard.evaluation.service.testsenario.connector.Connector;
import com.omarazzam.paymentguard.evaluation.service.testsenario.operator.Operator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ParseConditionService {

    @Autowired
    ObjectMapper objectMapper;


   public   boolean parseConditionThenEvaluate(Operator operator, ConditionDetails detail , PaymentTransactionEvaluation message) {

         Object messageFieldValue = getMessageFieldValue(detail.getConditionField(), message);
         Object conditionFieldValue = getTheConditionType(detail);

         return operator.evaluate(conditionFieldValue, messageFieldValue);
     }


    private Object getMessageFieldValue(String conditionField, PaymentTransactionEvaluation message) {
        Map<String, Object> mapMessage = objectMapper.convertValue(message, new TypeReference<Map<String, Object>>() {});

        for (Map.Entry<String, Object> entry : mapMessage.entrySet()) {
            if (entry.getKey().equals(conditionField)) {
                return entry.getValue();
            } else if (entry.getValue() instanceof Map) {
                Map<String, Object> nestedMap = (Map<String, Object>) entry.getValue();
                if (nestedMap.containsKey(conditionField)) {
                    return nestedMap.get(conditionField);
                }
            }
        }

        return null;
    }

    private Object getTheConditionType(ConditionDetails condDetail) {
        switch (condDetail.getConditionType()) {
            case "DECIMAL":
                return Double.parseDouble(condDetail.getConditionValue());
            case "STRING":
                return condDetail.getConditionValue();
            case "BOOLEAN":
                return Boolean.parseBoolean(condDetail.getConditionValue());
            case "DATE":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                return LocalDateTime.parse(condDetail.getConditionValue(), formatter);
            case "INTEGER":
                return Integer.parseInt(condDetail.getConditionValue());

        }
        return false;
    }

}
