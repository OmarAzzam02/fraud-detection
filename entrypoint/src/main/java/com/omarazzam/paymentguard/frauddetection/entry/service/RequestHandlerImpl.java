package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import com.omarazzam.paymentguard.frauddetection.entry.exception.LicenseIsNotValidException;
import com.omarazzam.paymentguard.frauddetection.entry.exception.NoServiceInstanceFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class RequestHandlerImpl implements  RequestHandler {

    @Autowired
    LicenceValidatorRequestHandlerService licenceValidatorRequestHandlerService;





    @Override
    public PaymentTransaction HandleValidatorRequest(PaymentTransaction message) throws LicenseIsNotValidException, NoServiceInstanceFoundException, Exception {
        try {
          return  licenceValidatorRequestHandlerService.sendRequestToLicenseValidator(message);
        }catch (NoServiceInstanceFoundException | LicenseIsNotValidException e){
            log.error(e);
            throw e;

        } catch (Exception e){
            log.error(e);
            throw e;
        }


    }

}
