package com.omarazzam.paymentguard.frauddetection.scenariomanager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.dao.UserScenarioDAO;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserScenario;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class UserScenarioServiceImpl implements UserScenarioService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserScenarioDAO db;

    @Autowired
    ObjectMapper objectMapper;

      public void CreateUserScenario(  UserScenario senario ) throws Exception{
          try {
          log.info("in scenario creation ");
          addScenarioToDataBase(senario);
          sendToEvaluation(senario);
          }catch (Exception e){
              log.error(e);
              throw e;
          }
        }
        public void addScenarioToDataBase( UserScenario senario){
          // add to database
            db.insertScenario(senario);
        }



        public void sendToEvaluation(  UserScenario senario ) throws Exception{

            log.info("sending to evaluation cashe");
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances( "EVALUATION" );
            if(serviceInstances.isEmpty()){
                log.trace("No Instance of Evaluation Or Evaluation Not Up at the moment ");
            }

            String url = serviceInstances.get(0).getUri().toURL() + "/add-user-scenario-to-cashe";
            ResponseEntity<?> response =  restTemplate.postForEntity(url, senario, String.class);

        }
}
