package com.omarazzam.paymentguard.frauddetection.scenariomanager.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserScenario {

    @JsonProperty("conditions")
    private Condition condition;
    @JsonProperty("senario_name")
    private String name;
    @JsonProperty("senario_type")
    private PayType senarioType;
}
