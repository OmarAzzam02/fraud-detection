package com.omarazzam.paymentguard.evaluation.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentTransactionEvaluation implements Serializable {
    @JsonProperty("transaction_id")
    private  String id;
    private double amount;
    private String currency;
    @JsonProperty("customer_details")
    private Map<String , Customer> customerDetails;
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


}
