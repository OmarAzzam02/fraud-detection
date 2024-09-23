    package com.omarazzam.paymentguard.evaluation.config;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
    import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionDLL;
    import org.apache.kafka.clients.consumer.ConsumerConfig;
    import org.apache.kafka.clients.producer.ProducerConfig;
    import org.apache.kafka.common.serialization.StringDeserializer;
    import org.apache.kafka.common.serialization.StringSerializer;
    import org.apache.kafka.streams.StreamsBuilder;
    import org.apache.kafka.streams.StreamsConfig;
    import org.springframework.cloud.client.discovery.DiscoveryClient;
    import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.jms.annotation.EnableJms;

    import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
    import org.springframework.kafka.config.KafkaListenerContainerFactory;
    import org.springframework.kafka.core.*;
    import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
    import org.springframework.kafka.listener.ContainerProperties;
    import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
    import org.springframework.kafka.support.serializer.JsonDeserializer;
    import org.springframework.kafka.support.serializer.JsonSerializer;
    import org.springframework.scheduling.annotation.EnableAsync;
    import org.springframework.web.client.RestTemplate;


    import java.util.*;

    @Configuration
    @EnableEurekaClient
    @EnableJms
    @EnableAsync
    public class EvaluationConfig {

        @Bean
        public List<UnifiedConditionDLL> userScenarios() {
            return new ArrayList<>();
        }

        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper;
        }


        @Bean
        public Map<String, Object> producerConfig() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

            return props;
        }

        @Bean
        public ProducerFactory<String, Object> producerFactory() {
            return new DefaultKafkaProducerFactory<>(producerConfig());
        }


        @Bean
        public KafkaTemplate<String, Object> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }





        @Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }


    }
