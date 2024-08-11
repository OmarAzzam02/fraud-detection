package com.omarazzam.paymentguard.frauddetection.entry.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
@EnableCaching
@EnableEurekaClient
public class EntryPointConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
