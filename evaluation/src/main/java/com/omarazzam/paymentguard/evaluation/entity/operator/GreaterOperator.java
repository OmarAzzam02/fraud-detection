package com.omarazzam.paymentguard.evaluation.entity.operator;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log4j2
@Component
public class GreaterOperator implements Operator<BigDecimal> {

    @Override
    public boolean evaluate(BigDecimal messageValue, BigDecimal conditionValue) {

        return messageValue.compareTo(conditionValue) > 0;


    }
}

