package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.omarazzam.paymentguard.frauddetection.entry.exception.LicenseIsNotValidException;
import com.omarazzam.paymentguard.frauddetection.entry.exception.NoServiceInstanceFoundException;

public interface RequestHandler {
     void HandleValidatorRequest(String message) throws LicenseIsNotValidException , NoServiceInstanceFoundException , Exception;

}
