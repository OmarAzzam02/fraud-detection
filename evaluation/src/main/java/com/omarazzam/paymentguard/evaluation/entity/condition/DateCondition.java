package com.omarazzam.paymentguard.evaluation.entity.condition;

import com.omarazzam.paymentguard.evaluation.entity.operator.Operator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class DateCondition implements Condition {

    String field;
    LocalDateTime value;
    Operator<LocalDateTime> operator;


    @Override
    public boolean evaluate(Object data) {
        return false;
    }

    @Override
    public String getField() {
        return field;
    }
}
