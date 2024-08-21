package com.omarazzam.paymentguard.evaluation.service.testsenario.operator;

import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.evaluation.entity.senario.ConditionDetails;
import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;

public class LessOperator implements Operator{




    @Override
    public  boolean evaluate(Object messageValue, Object  conditionValue) {
        return ((Comparable) messageValue).compareTo(conditionValue) > 0;
    }
}
