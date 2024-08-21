package com.omarazzam.paymentguard.evaluation.service.testsenario.operator;


import org.springframework.stereotype.Component;

@Component
public class OperatorFactory {



     public Operator createOperator(String operator) {

        switch (operator) {
            case ">" : return new GreaterOperator();
            case "<" : return new LessOperator();
            case "=" : return new EqualOperator();
            case ">=": return new GreaterOrEqualOperator();
            case "<=": return new LessOrEqualOperator();
            default : return null;
        }
    }

}
