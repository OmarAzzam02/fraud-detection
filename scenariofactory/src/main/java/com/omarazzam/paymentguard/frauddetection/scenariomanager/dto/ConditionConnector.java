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
public class ConditionConnector {
    @JsonProperty("condition_connector")
    String connection;
    @JsonProperty("condition_left_id")
    int leftId;
    @JsonProperty("condition_right_id")
    int rightId;
}
