package com.omarazzam.paymentguard.frauddetection.scenariomanager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.dao.UserScenarioDAO;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.dao.UserScenarioDAOImpl;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Map;

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

      public void CreateUserScenario( @NonNull String jsonScenario ) throws Exception{
          try {
          Map<String , ?> scenario = objectMapper.readValue(jsonScenario, Map.class);
          addScenarioToDataBase(scenario);
          sendToEvaluation(scenario);

          }catch (Exception e){
              log.error(e);
              throw e;
          }

        }
        public void addScenarioToDataBase( @NonNull Map<String , ?> scenario ){
          // add to database
            db.insertScenario(scenario);
        }



        public void sendToEvaluation( @NonNull Map<String , ?> scenario ) throws Exception{
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances( "EVALUATION" );
            if(serviceInstances.isEmpty())
                return;

            String url = serviceInstances.get(0).getUri().toURL() + "/add-user-scenario-to-cashe";
            ResponseEntity<?> response =  restTemplate.getForEntity(url, String.class);
            // if response fails
            // will save to schedualar
            // then will send it again after a scpecific amount of time

        }




}
