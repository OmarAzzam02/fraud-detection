package com.omarazzam.paymentguard.frauddetection.scenariomanager.service;


import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserScenario;
import org.springframework.scheduling.annotation.Async;

public interface UserScenarioService {
    
     void CreateUserScenario(  UserScenario senario ) throws Exception;
     @Async
     void addScenarioToDataBase(  UserScenario senario) throws Exception;

     void sendToEvaluation(  UserScenario senario ) throws Exception;
}
