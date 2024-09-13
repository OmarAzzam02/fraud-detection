package com.omarazzam.paymentguard.evaluation.controller;



import com.omarazzam.paymentguard.evaluation.dto.scenarioDTO.UserScenarioDTO;
import com.omarazzam.paymentguard.evaluation.service.ConstructScenarioUnifiedCondition;
import com.omarazzam.paymentguard.evaluation.service.UserSenarioCashe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/")
public class EvaluationController {

    @Autowired
    UserSenarioCashe userSenarioCashe;

    @Autowired
    ConstructScenarioUnifiedCondition constructScenarioUnifiedCondition;

    @PostMapping("/add-user-scenario-to-cashe")
    void addUserScenarioToCashe( @RequestBody UserScenarioDTO scenario) {

        constructScenarioUnifiedCondition.createUnifiedCondition(Arrays.asList(scenario));
    }
}
