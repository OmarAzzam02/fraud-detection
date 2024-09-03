package com.omarazzam.paymentguard.frauddetection.licencevalidator.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Licence {
    @JsonProperty("pay_type")
     private PayType payType;
    @JsonProperty("reference_number")
    private String referenceNumber;
}
