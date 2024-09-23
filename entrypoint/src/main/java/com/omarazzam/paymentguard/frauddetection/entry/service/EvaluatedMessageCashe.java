package com.omarazzam.paymentguard.frauddetection.entry.service;


import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@Getter
@Setter
@Data
public class EvaluatedMessageCashe {

    @Autowired
    private Map<Long, PaymentTransaction> evaluatedMessages;


    public void addTransaction(PaymentTransaction transaction) {
        log.info("Adding transaction in MAP {} " , transaction.getId());
        evaluatedMessages.put(transaction.getId(), transaction);
    }

    public void removeTransaction(PaymentTransaction transaction) {
        evaluatedMessages.remove(transaction.getId());
    }

    public boolean isResponeBack(long payId) {
        return evaluatedMessages.containsKey(payId);
    }



}
