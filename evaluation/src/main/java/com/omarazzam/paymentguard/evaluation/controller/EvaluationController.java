package com.omarazzam.paymentguard.evaluation.controller;


import com.omarazzam.paymentguard.evaluation.service.CasheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class EvaluationController {

    @Autowired
    CasheService casheService;

    @PostMapping("/add-user-scenario-to-cashe")
    void addUserScenarioToCashe(Map<String, ?> scenario) {
        casheService.addSenarioToCashe(scenario);
    }
}
