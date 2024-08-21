package com.omarazzam.paymentguard.evaluation.service.testsenario.operator;

import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.evaluation.entity.senario.ConditionDetails;
import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;

public interface Operator {

    boolean evaluate(Object messageValue, Object  conditionValue);
}
