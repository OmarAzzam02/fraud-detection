package com.omarazzam.paymentguard.frauddetection.scenariomanager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ScenarioFactoryApplication {



    public static void main(String[] args) {
        SpringApplication.run(ScenarioFactoryApplication.class, args);
    }
}
