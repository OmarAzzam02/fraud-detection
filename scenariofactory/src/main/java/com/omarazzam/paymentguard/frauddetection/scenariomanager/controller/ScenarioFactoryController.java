package com.omarazzam.paymentguard.frauddetection.scenariomanager.controller;


import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.*;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.service.UserScenarioServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class ScenarioFactoryController {

    @Autowired
    UserScenarioServiceImpl userScenarioService;

    @PostMapping("/create")
    ResponseEntity<?> CreateUserScenario(@RequestBody UserScenario senario){
        log.info("Recieved scenario: {} ", senario);
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

            List<UserScenario>  scenarios = getScenarios();
            return ResponseEntity.ok().body(scenarios);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    List<UserScenario> getScenarios() {
        List<UserScenario> scenarios = new ArrayList<>();

        for (int i = 1; i <=20; i++) {
            UserScenario scenario = UserScenario.builder()
                    .name("Scenario " + i)
                    .senarioType(PayType.MOBILE)
                    .condition(
                            Condition.builder()
                                    .conditionStr("Amount > " + 1000 + " & pay_type = MOBILE & status = PENDING")
                                    .connectors(Arrays.asList(
                                            new ConditionConnector("&", 101 + i, 102 + i),
                                            new ConditionConnector("&", 102 + i, 103 + i),
                                            new ConditionConnector("&", 103 + i, 104 + i )

                                    ))
                                    .details(Arrays.asList(
                                            new ConditionDetails(101 + i, "amount", String.valueOf(1000), ">", "DECIMAL"),
                                            new ConditionDetails(102 + i, "pay_type", "MOBILE", "=", "STRING"),
                                            new ConditionDetails(103 + i, "status", "PENDING", "=", "STRING"),
                                            new ConditionDetails(104 + i, "address.country", "Jordan", "=", "STRING")


                                    ))
                                    .build()
                    )
                    .build();

            scenarios.add(scenario);
        }
        log.info("done intializong scenarios");
        return scenarios;
    }

}
