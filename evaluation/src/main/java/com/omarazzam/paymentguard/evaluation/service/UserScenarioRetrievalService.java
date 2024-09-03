package com.omarazzam.paymentguard.evaluation.service;

import com.omarazzam.paymentguard.evaluation.dto.scenarioDTO.UserScenarioDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    ConstructScenarioUnifiedCondition constructScenarioUnifiedCondition;

    @Autowired
    UserSenarioCashe userSenarioCashe;
    @Autowired
    DiscoveryClient discoveryClient;

    @PostConstruct
    void init() {
        try {
            log.info("getting the info from scenarios in init function");
            List<ServiceInstance> list = discoveryClient.getInstances("SCENARIO-FACTORY");
            log.info(list.get(0).getUri().toURL());
            String url = list.get(0).getUri().toURL() + "/retrieve-scenarios";
            ResponseEntity<List<UserScenarioDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UserScenarioDTO>>() {
                    });
            constructScenarioUnifiedCondition.createUnifiedCondition(response.getBody());

        } catch (Exception e) {
            log.error(e);
        }
    }
}
