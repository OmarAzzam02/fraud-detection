package com.omarazzam.paymentguard.evaluation.controller;



import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;
import com.omarazzam.paymentguard.evaluation.service.UserSenarioCashe;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/")
public class EvaluationController {

    @Autowired
    UserSenarioCashe userSenarioCashe;

    @PostMapping("/add-user-scenario-to-cashe")
    void addUserScenarioToCashe( @RequestBody UserSenario senario) {
        log.info("in add-user-scenario-to-cashe  controller {} ", senario   );
        userSenarioCashe.addSenarioToCashe(senario);
    }
}
