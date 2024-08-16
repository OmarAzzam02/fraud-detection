package com.omarazzam.paymentguard.frauddetection.scenariomanager.controller;


import com.omarazzam.paymentguard.frauddetection.scenariomanager.service.UserScenarioServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/")
public class ScenarioFactoryController {

    @Autowired
    UserScenarioServiceImpl userScenarioService;

    @PostMapping("/create")
    ResponseEntity<?> CreateUserScenario(@RequestBody String scenario){
        log.info("Recieved scenario: " + scenario);
        try {
            if (scenario==null || scenario.isEmpty())
                throw new Exception("Scenario name cannot be empty");
            userScenarioService.CreateUserScenario(scenario);

        }catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/retrieve-scenarios")
    ResponseEntity<?> retrieveScenarios(){
        log.info("in retrieve Scenario");
        return ResponseEntity.ok().body("info for evaluation");
    }
}
