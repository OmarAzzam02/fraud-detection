package com.omarazzam.paymentguard.evaluation.entity.connector;


import com.omarazzam.paymentguard.evaluation.entity.condition.Condition;
import org.springframework.stereotype.Component;

@Component
public class AndConnector implements Connector {

    @Override
    public boolean evaluate(boolean leftCondition, boolean rightCondition) {
        return leftCondition && rightCondition;
    }
}
