package com.omarazzam.paymentguard.evaluation.entity.condition;

import com.omarazzam.paymentguard.evaluation.entity.operator.Operator;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;


@Log4j2
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecimalCondition implements Condition {

    private String field;
    private Operator<BigDecimal> operator;
    private BigDecimal value;


    @Override
    public boolean evaluate(Object data) {

        try {
            BigDecimal messageValue = convertData(data);
            return operator.evaluate(messageValue, value);
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }

    @Override
    public String getField() {
        return field;
    }


    private  BigDecimal convertData(Object data){

        if(data instanceof  Long)
            return BigDecimal.valueOf((Long) data);
        if (data instanceof  Integer)
            return BigDecimal.valueOf((Integer) data);
        if (data instanceof  Double)
            return BigDecimal.valueOf((Double) data);
        if (data instanceof  Float)
            return BigDecimal.valueOf((Float) data);
        if(data instanceof  BigDecimal)
            return (BigDecimal) data;

        return null;
    }




}