package com.omarazzam.paymentguard.frauddetection.scenariomanager.dao;
import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserScenario;
import org.springframework.scheduling.annotation.Async;


public interface UserScenarioDAO {

    @Async
    void insertScenario(UserScenario senario);
}
