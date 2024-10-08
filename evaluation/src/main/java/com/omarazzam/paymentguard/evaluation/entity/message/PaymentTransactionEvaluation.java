package com.omarazzam.paymentguard.evaluation.entity.message;
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
    private  long id;
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
