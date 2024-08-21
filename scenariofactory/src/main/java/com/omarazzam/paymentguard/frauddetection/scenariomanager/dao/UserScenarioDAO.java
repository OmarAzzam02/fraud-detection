package com.omarazzam.paymentguard.frauddetection.scenariomanager.dao;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserSenario;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;



public interface UserScenarioDAO {

    @Async
    void insertScenario(UserSenario senario);
}
