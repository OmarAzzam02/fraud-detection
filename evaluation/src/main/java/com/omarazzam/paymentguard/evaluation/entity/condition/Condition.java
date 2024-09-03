package com.omarazzam.paymentguard.evaluation.entity.condition;

public interface Condition {
    boolean evaluate(Object data);
    String getField();
}
