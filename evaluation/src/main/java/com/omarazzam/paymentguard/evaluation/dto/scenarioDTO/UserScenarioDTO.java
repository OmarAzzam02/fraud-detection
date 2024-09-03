package com.omarazzam.paymentguard.evaluation.dto.scenarioDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserScenarioDTO {

    @JsonProperty("conditions")
    private ConditionDTO condition;
    @JsonProperty("senario_name")
    private String name;
    @JsonProperty("senario_type")
    private PayType senarioType;
}
