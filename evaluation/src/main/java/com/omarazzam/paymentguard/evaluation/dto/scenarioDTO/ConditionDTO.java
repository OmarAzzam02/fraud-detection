package com.omarazzam.paymentguard.evaluation.dto.scenarioDTO;

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
public class ConditionDTO {

    @JsonProperty("conditionString")
    private String conditionStr;
    @JsonProperty("condition_connectors")
    private List<ConditionConnectorDTO> connectors;
    @JsonProperty("condition_details")
    private List<ConditionDetailsDTO> details;
}