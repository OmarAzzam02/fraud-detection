package com.omarazzam.paymentguard.frauddetection.licencevalidator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class LicenceValidatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicenceValidatorApplication.class, args);
    }
}
