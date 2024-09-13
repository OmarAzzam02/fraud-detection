    package com.omarazzam.paymentguard.evaluation.config;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
    import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionDLL;
    import org.apache.kafka.clients.consumer.ConsumerConfig;
    import org.apache.kafka.common.serialization.StringDeserializer;
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
    import org.springframework.kafka.core.ConsumerFactory;
    import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
    import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
    import org.springframework.kafka.listener.ContainerProperties;
    import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
    import org.springframework.kafka.support.serializer.JsonDeserializer;
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
            mapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
            return mapper;
        }




        @Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

    //    @Bean
    //    public Map<String, Object> consumerConfig() {
    //        Map<String, Object> props = new HashMap<>();
    //        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
    //                "localhost:9092");
    //        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
    //                StringDeserializer.class);
    //        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
    //                JsonDeserializer.class);
    //        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    //        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, PaymentTransactionEvaluation.class);
    //        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    //        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    //
    //        return props;
    //    }

    //    @Bean
    //    public ConsumerFactory<String,Object> consumerFactory(){
    //        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    //    }
    //
    //    @Bean
    //    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
    //        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
    //                new ConcurrentKafkaListenerContainerFactory<>();
    //        factory.setConsumerFactory(consumerFactory());
    //        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    //        return factory;
    //    }
    }
