package com.omarazzam.paymentguard.evaluation.service.testsenario.operator;

public class GreaterOrEqualOperator implements Operator{
    @Override
    public boolean evaluate(Object messageValue, Object conditionValue) {
        return ((Comparable) messageValue).compareTo(conditionValue) >= 0;
    }
}
