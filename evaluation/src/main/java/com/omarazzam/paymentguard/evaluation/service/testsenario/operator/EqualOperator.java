package com.omarazzam.paymentguard.evaluation.service.testsenario.operator;
public class EqualOperator implements Operator {



    @Override
    public boolean evaluate(Object messageValue, Object conditionValue ) {
        return  messageValue.equals(conditionValue);
    }


}
