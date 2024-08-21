package com.omarazzam.paymentguard.evaluation.service;



import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class UserScenarioRetrievalService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EvaluateMessageService evaluateMeesageService;
    @Autowired
    UserSenarioCashe userSenarioCashe;
    @Autowired
    DiscoveryClient discoveryClient;
    @PostConstruct
    void init(){
        try {


            log.info("getting the info from scenarios in init function");
            List<ServiceInstance> list = discoveryClient.getInstances("SCENARIO-FACTORY");
            log.info(list.get(0).getUri().toURL());
            String url = list.get(0).getUri().toURL()+"/retrieve-scenarios";
            ResponseEntity<UserSenario> response =  restTemplate.getForEntity(url, UserSenario.class);
            log.info(response.getStatusCode() +  "  "  + response.getBody().getClass() + response.getBody());
            userSenarioCashe.addCollectionOfScenariosToCashe(Arrays.asList(response.getBody()));



        }catch (Exception e){
            log.error(e);
        }
    }


}
