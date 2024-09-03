package com.omarazzam.paymentguard.evaluation.entity.condition;

import com.omarazzam.paymentguard.evaluation.entity.operator.Operator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StringCondition implements  Condition{

    String field;
    Operator<String> operator;
    String value;




    @Override
    public boolean evaluate(Object data) {

        try {
        String messageValue =  data.toString();
        return operator.evaluate(messageValue , value);
        }catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    @Override
    public String getField() {
        return field;
    }


}
