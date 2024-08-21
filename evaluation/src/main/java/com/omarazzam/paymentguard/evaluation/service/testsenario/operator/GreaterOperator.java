package com.omarazzam.paymentguard.evaluation.service.testsenario.operator;

public class GreaterOperator implements  Operator{

    @Override
    public boolean evaluate(Object conditionValue, Object  messageValue) {

        return ((Comparable) messageValue).compareTo(conditionValue) > 0;
    }
}

