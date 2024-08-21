package com.omarazzam.paymentguard.evaluation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;

import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;
import com.omarazzam.paymentguard.evaluation.service.testsenario.connector.Connector;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableEurekaClient
@EnableJms
public class EvaluationConfig {




    @Bean
    public List<UserSenario> userScenarios() {
        return new ArrayList<>();
    }


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        return connectionFactory;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        return pooledConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(PooledConnectionFactory pooledConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter());
        factory.setConnectionFactory(pooledConnectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction", PaymentTransactionEvaluation.class);
        converter.setTypeIdMappings(typeIdMappings);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
