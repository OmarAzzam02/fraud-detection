package com.omarazzam.paymentguard.evaluation.entity.operator;


import org.bouncycastle.operator.OperatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorFactory {

@Autowired
GreaterOperator greaterOperator;
@Autowired
EqualOperator equalOperator;
@Autowired
GreaterOrEqualOperator greaterOrEqualOperator;
@Autowired
LessOperator lessOperator;
@Autowired
LessOrEqualOperator lessOrEqualOperator;


     public Operator createOperator(String operator) throws OperatorException {

        switch (operator) {
            case ">" : return  greaterOperator;
            case "<" : return  lessOperator;
            case "=" : return  equalOperator;
            case ">=": return  greaterOrEqualOperator;
            case "<=": return  lessOrEqualOperator;
            default : throw new OperatorException("Invalid operator: " + operator);
        }
    }

}
