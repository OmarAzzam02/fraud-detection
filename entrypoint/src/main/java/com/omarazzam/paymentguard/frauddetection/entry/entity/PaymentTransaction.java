package com.omarazzam.paymentguard.frauddetection.entry.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;



@Data
@Getter
@Setter
@ToString
public class PaymentTransaction  implements Serializable {
     @JsonProperty("transaction_id")
     private long id;
     private double amount;
     private String currency;
     @JsonProperty("customer_receiver")
     private Customer reciever ;
     @JsonProperty("customer_sender")
     private Customer  sender ;
     private String description;
     private String notes;
     @JsonProperty("pay_type")
     private PayType payType;
     @JsonProperty("payment_method")
     private String paymentMethod;
     @JsonProperty("reference_number")
     private String referenceNumber;
     private TransactionStatus status;
     @JsonProperty("transaction_date")
     private LocalDateTime transactionDate;
     @JsonProperty("address")
     private Address address;
     @JsonProperty("fraud_status")
     private FraudStatus flag;
}
