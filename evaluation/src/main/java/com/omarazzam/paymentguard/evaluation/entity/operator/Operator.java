package com.omarazzam.paymentguard.evaluation.entity.operator;

public interface Operator<T> {

    boolean evaluate(T messageValue,T  conditionValue);
}
