package com.omarazzam.paymentguard.evaluation.entity.operator;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LessOperator implements Operator<BigDecimal>{





    @Override
    public boolean evaluate(BigDecimal messageValue, BigDecimal conditionValue) {
        return messageValue.compareTo(conditionValue) < 0 ;
    }
}
