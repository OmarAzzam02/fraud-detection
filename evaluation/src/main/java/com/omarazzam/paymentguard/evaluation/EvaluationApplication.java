package com.omarazzam.paymentguard.evaluation;


import com.omarazzam.paymentguard.evaluation.service.UserScenarioRetrievalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class EvaluationApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvaluationApplication.class, args);
    }
}
