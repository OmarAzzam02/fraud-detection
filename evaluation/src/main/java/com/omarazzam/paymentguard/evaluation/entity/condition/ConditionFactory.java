package com.omarazzam.paymentguard.evaluation.entity.condition;

import com.omarazzam.paymentguard.evaluation.entity.operator.Operator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ConditionFactory {

    public Condition createCondition(String fieldType , String field, String value , Operator<?> operator ) {
        switch (fieldType.toUpperCase()) {

            case "STRING": return StringCondition.builder().field(field).value(value).operator((Operator<String>) operator).build();
            case "DATE": return  DateCondition.builder().field(field).value(LocalDateTime.parse(value)).operator((Operator<LocalDateTime>) operator).build();
            case "DECIMAL": return  DecimalCondition.builder().field(field).value(new BigDecimal(value)).operator((Operator<BigDecimal>) operator).build();
            default: throw new IllegalArgumentException("Invalid condition type: " + field);

        }
    }

}
