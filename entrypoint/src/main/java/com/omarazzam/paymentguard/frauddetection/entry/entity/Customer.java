package com.omarazzam.paymentguard.frauddetection.entry.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    @JsonProperty("account_number")
    private String accountNumber;
}
