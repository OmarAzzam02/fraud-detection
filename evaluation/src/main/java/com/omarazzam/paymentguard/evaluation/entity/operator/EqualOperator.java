package com.omarazzam.paymentguard.evaluation.entity.operator;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class EqualOperator implements Operator<String> {

    @Override
    public boolean evaluate(String messageValue, String conditionValue) {
         return messageValue.equals(conditionValue);
    }
}
