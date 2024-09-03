package com.omarazzam.paymentguard.evaluation.entity.condition;

import com.omarazzam.paymentguard.evaluation.entity.connector.Connector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class FullCondition {

    Condition condition;
    Connector connector;

}
