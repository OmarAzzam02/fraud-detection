package com.omarazzam.paymentguard.frauddetection.entry.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseDTO {


    @JsonProperty("pay_type")
    private PayType payType;
    @JsonProperty("reference_number")
    private String referenceNumber;



}
