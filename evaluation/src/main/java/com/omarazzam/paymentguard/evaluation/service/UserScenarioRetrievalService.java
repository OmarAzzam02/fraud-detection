package com.omarazzam.paymentguard.evaluation.service;



import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Log4j2
@Service
public class UserScenarioRetrievalService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;
    @PostConstruct
    void init(){
        try {
            List<ServiceInstance> list = discoveryClient.getInstances("SCENARIO-FACTORY");
            log.info(list.get(0).getUri().toURL());
            String url = list.get(0).getUri().toURL()+"/retrieve-scenarios";
            ResponseEntity<?> response =  restTemplate.getForEntity(url, String.class);
            log.info(response.getStatusCode());

            // cashee the senarios

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void getScenarios() {
    }
}
