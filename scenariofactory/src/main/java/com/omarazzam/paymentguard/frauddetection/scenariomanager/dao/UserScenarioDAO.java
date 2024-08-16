package com.omarazzam.paymentguard.frauddetection.scenariomanager.dao;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;



public interface UserScenarioDAO {

    @Async
    void insertScenario(Map<String , ?> scenarioMap);
}
