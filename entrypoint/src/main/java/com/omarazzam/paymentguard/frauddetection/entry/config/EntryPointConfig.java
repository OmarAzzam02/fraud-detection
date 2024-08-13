    package com.omarazzam.paymentguard.frauddetection.entry.config;


    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.databind.SerializationFeature;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import org.apache.activemq.ActiveMQConnectionFactory;
    import org.apache.activemq.jms.pool.PooledConnectionFactory;
    import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
    import org.springframework.jms.core.JmsTemplate;
    import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
    import org.springframework.jms.support.converter.MessageConverter;
    import org.springframework.jms.support.converter.MessageType;
    import org.springframework.scheduling.annotation.EnableAsync;
    import org.springframework.web.client.RestTemplate;

    @Configuration
    @EnableAsync
    @EnableEurekaClient
    public class EntryPointConfig {
        @Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }


        @Bean
        public ActiveMQConnectionFactory activeMQConnectionFactory() {
            return new ActiveMQConnectionFactory("tcp://localhost:61616");
        }

        @Bean
        public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setConnectionFactory(activeMQConnectionFactory);
            return factory;
        }

        @Bean
        public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory, MessageConverter messageConverter) {
            JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
            jmsTemplate.setMessageConverter(messageConverter);
            return jmsTemplate;
        }

        @Bean
        public MessageConverter messageConverter() {
            MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
            converter.setTargetType(MessageType.TEXT);
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
    }



