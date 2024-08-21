package com.omarazzam.paymentguard.frauddetection.scenariomanager.service;


import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserSenario;
import lombok.NonNull;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface UserScenarioService {
    
     void CreateUserScenario( @NonNull UserSenario  senario ) throws Exception;
     @Async
     void addScenarioToDataBase( @NonNull UserSenario  senario) throws Exception;

     void sendToEvaluation( @NonNull UserSenario  senario ) throws Exception;
}
