package com.omarazzam.paymentguard.evaluation.entity.connector;


import com.omarazzam.paymentguard.evaluation.entity.condition.Condition;

public interface Connector {
    boolean evaluate(boolean leftCondition, boolean rightCondition);
}
