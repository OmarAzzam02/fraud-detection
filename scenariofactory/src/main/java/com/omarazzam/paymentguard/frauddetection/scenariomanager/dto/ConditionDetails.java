package com.omarazzam.paymentguard.frauddetection.scenariomanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionDetails {

    @JsonProperty("condition_id")
    int conditionId;
    @JsonProperty("condition_field")
    private String conditionField;
    @JsonProperty("condition_value")
    private String conditionValue;
    @JsonProperty("condition_operator")
    private String conditionOperator;
    @JsonProperty("condition_type")
    private String conditionType;
}
