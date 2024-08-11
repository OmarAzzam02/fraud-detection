package com.omarazzam.paymentguard.frauddetection.entry.service;


import com.omarazzam.paymentguard.frauddetection.entry.exception.NoServiceInstanceFoundException;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.List;

@Service
@Log4j2
@Builder
public class LicenceValidatorRequestHandlerService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private SendMessageToEvaluation sendMessageToEvaluationImpl;



    public void sendRequestToLicenseValidator(String message) throws Exception {
            log.info(" Sending request to license validator {} " , message);
            List<ServiceInstance> list = getLicenseValidatorInstances();
             if(list.isEmpty())
                 throw  new NoServiceInstanceFoundException("No Service Instance Found for LicenceValidator");
             try {
                 send(list, message);
             }catch (Exception e) {
                 log.error(e);
                 throw e;
             }

         // async
        sendMessageToEvaluationImpl.sendMessage(message);
    }



    private List<ServiceInstance> getLicenseValidatorInstances() {
        return discoveryClient.getInstances("LICENCE-VALIDATOR");
    }

    private  void send(List<ServiceInstance> list  , String message) throws Exception {
            log.info("sending....");
            String url  = list.get(0).getUri().toURL() + "/license-service/validate";
            ResponseEntity<?> response =   restTemplate.postForEntity( url, message , String.class);

    }


}
