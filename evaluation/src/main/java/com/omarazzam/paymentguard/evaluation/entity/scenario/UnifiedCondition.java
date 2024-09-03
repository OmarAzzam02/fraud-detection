package com.omarazzam.paymentguard.evaluation.entity.scenario;

import com.omarazzam.paymentguard.evaluation.dto.scenarioDTO.PayType;
import com.omarazzam.paymentguard.evaluation.entity.condition.FullCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnifiedCondition {
    FullCondition fullCondition;
    String scenarioName;
    PayType payType;
}
