package org.eastnets.paymentguard.frauddetection.entry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EntryPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntryPointApplication.class, args);
    }
}
