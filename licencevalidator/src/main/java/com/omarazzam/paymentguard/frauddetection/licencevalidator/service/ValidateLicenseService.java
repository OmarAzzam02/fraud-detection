package com.omarazzam.paymentguard.frauddetection.licencevalidator.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class ValidateLicenseService {


    public boolean isValidLicense(String message) throws Exception {

        if(message == null)
            throw new Exception("Message is Empty ");

        // implement logic for license



        return true;
    }

}
