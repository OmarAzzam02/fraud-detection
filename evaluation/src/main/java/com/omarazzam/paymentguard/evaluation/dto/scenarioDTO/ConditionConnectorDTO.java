package com.omarazzam.paymentguard.evaluation.dto.scenarioDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionConnectorDTO {
    @JsonProperty("condition_connector")
    String connector;
    @JsonProperty("condition_left_id")
    int leftId;
    @JsonProperty("condition_right_id")
    int rightId;
}