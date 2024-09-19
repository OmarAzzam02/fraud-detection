package com.omarazzam.paymentguard.frauddetection.licencevalidator.config;


import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.ValidRefrence;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableEurekaClient
public class LicenseValidatorConfig {


    @Bean
    List<ValidRefrence> validRefrences() {
        return new ArrayList<>();
    }



}
