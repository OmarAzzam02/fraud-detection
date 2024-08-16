package com.omarazzam.paymentguard.frauddetection.scenariomanager.service;


import lombok.NonNull;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface UserScenarioService {
    
     void CreateUserScenario( @NonNull String jsonScenario ) throws Exception;
     void addScenarioToDataBase( @NonNull Map<String , ?> jsonScenario ) throws Exception;
     @Async
     void sendToEvaluation( @NonNull Map<String , ?> scenario ) throws Exception;
}
