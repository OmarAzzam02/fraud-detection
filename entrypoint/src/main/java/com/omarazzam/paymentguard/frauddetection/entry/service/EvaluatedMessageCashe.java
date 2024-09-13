package com.omarazzam.paymentguard.frauddetection.entry.service;


import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Getter
@Setter
@Data
public class EvaluatedMessageCashe {

    @Autowired
     private Map<Long,PaymentTransaction> evaluatedMessages;


   public void addTransaction(PaymentTransaction transaction) {
       evaluatedMessages.put(transaction.getId(),transaction);
    }

    public void removeTransaction(PaymentTransaction transaction) {
       evaluatedMessages.remove(transaction.getId());
    }


    public boolean isResponeBack(long payId){
      return  getTransaction(payId) != null;
    }

    public PaymentTransaction getTransaction(long payId){
       return evaluatedMessages.get(payId);
    }
}
