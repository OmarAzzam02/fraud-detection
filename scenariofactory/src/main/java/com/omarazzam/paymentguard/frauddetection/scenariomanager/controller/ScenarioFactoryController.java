package com.omarazzam.paymentguard.frauddetection.scenariomanager.controller;


import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.*;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.service.UserScenarioServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class ScenarioFactoryController {

    @Autowired
    UserScenarioServiceImpl userScenarioService;

    @PostMapping("/create")
    ResponseEntity<?> CreateUserScenario(@RequestBody UserSenario senario){
        log.info("Recieved scenario: " + senario);
        try {
            if (senario.getCondition()==null || senario.getName() == null)
                throw new Exception("Scenario name cannot be empty");
            userScenarioService.CreateUserScenario(senario);

        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/retrieve-scenarios")
    ResponseEntity<?> retrieveScenarios(){
        log.info("in retrieve Scenario controller");
        try {

            UserSenario userSenario = getUserScenario();

            return ResponseEntity.ok().body(userSenario);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    UserSenario getUserScenario () {
        return UserSenario.builder()
                .name("Mobile Payment Validation")
                .senarioType(PayType.MOBILE)
                .condition(
                        Condition.builder()
                                .conditionStr("Amount > 50 & pay_type = MOBILE | payment_method = Online Banking & status = PENDING")
                                .connectors(Arrays.asList(
                                        new ConditionConnector("&", 201, 202),
                                        new ConditionConnector("|", 202, 203),
                                        new ConditionConnector("&", 203, 204)
                                ))
                                .details(Arrays.asList(
                                        new ConditionDetails(201, "amount", "50", ">", "DECIMAL"),
                                        new ConditionDetails(202, "pay_type", "MOBILE", "=", "STRING"),
                                        new ConditionDetails(203, "payment_method", "Online Banking", "=", "STRING"),
                                        new ConditionDetails(204, "status", "PENDING", "=", "STRING")
                                ))
                                .build()
                )
                .build();
        }
    }
