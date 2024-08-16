package com.omarazzam.paymentguard.frauddetection.scenariomanager.config;


import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableEurekaClient
@EnableAsync(proxyTargetClass = true)
public class ScenarioFactoryApplicationConfig {


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
