package com.omarazzam.paymentguard.frauddetection.licencevalidator.service;

import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.Licence;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Log4j2
@Service
public class ValidateLicenseService {



    @Autowired
    private ValidReferenceCache validReferences;


    public boolean isValidLicense(Licence message) throws Exception {
        if (message == null || message.getPayType() == null || message.getReferenceNumber() == null || message.getReferenceNumber().length() < 4) {
            throw new Exception("Invalid message or missing data");
        }


        String payType = message.getPayType().toString();
        String referencePrefix = message.getReferenceNumber().substring(0, 4);


        return validReferences.getValidRefrences().stream()
                .filter(validRef -> validRef.getPayType().toString().equalsIgnoreCase(payType))
                .flatMap(validRef -> validRef.getReff().stream())
                .anyMatch(referencePrefix::equalsIgnoreCase);
    }
}