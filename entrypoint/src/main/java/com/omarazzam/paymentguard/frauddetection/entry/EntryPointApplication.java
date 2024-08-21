package com.omarazzam.paymentguard.frauddetection.entry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication

public class EntryPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntryPointApplication.class, args);
    }
}
