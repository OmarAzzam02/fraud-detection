package com.omarazzam.paymentguard.frauddetection.entry.service;

import com.omarazzam.paymentguard.frauddetection.entry.entity.LicenseDTO;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Log4j2
@Builder
public class LicenceValidatorRequestHandlerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SendMessageToEvaluation sendMessageToEvaluationImpl;



    public PaymentTransaction sendRequestToLicenseValidator(final PaymentTransaction message) throws Exception {
            log.info(" Sending request to license validator {} " , message);

             try {

                 send(message);

             }catch(Exception e) {
                 log.error(e);
                 throw e;
             }

       return  sendMessageToEvaluationImpl.sendMessage(message);
    }


    private  void send(final PaymentTransaction message) throws Exception {
            LicenseDTO licenseDTO = LicenseDTO.builder().payType(message.getPayType()).referenceNumber(message.getReferenceNumber()).build();
;
            String url  = "http://LICENCE-VALIDATOR/license-service/validate";
            ResponseEntity<String> response = restTemplate.postForEntity(url, licenseDTO, String.class);
    }


}
