package com.omarazzam.paymentguard.frauddetection.scenariomanager.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Condition{

    @JsonProperty("conditionString")
    private String conditionStr;
    @JsonProperty("condition_connectors")
    private List<ConditionConnector> connectors;
    @JsonProperty("condition_details")
    private List<ConditionDetails> details;
}
