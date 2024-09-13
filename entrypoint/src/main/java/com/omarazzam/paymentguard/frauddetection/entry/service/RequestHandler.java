package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import com.omarazzam.paymentguard.frauddetection.entry.exception.LicenseIsNotValidException;
import com.omarazzam.paymentguard.frauddetection.entry.exception.NoServiceInstanceFoundException;

public interface RequestHandler {
     PaymentTransaction HandleValidatorRequest(PaymentTransaction message) throws LicenseIsNotValidException , NoServiceInstanceFoundException , Exception;

}
