package com.omarazzam.paymentguard.evaluation.controller;


import com.omarazzam.paymentguard.evaluation.service.CasheService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/")
public class EvaluationController {

    @Autowired
    CasheService casheService;

    @PostMapping("/add-user-scenario-to-cashe")
    void addUserScenarioToCashe( @RequestBody  Map<String, ?> scenario) {
        log.info("in add-user-scenario-to-cashe  controller ");
        casheService.addSenarioToCashe(scenario);
    }
}
