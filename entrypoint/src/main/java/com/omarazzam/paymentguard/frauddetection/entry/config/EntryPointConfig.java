    package com.omarazzam.paymentguard.frauddetection.entry.config;




    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
    import org.apache.activemq.ActiveMQConnectionFactory;
    import org.apache.activemq.jms.pool.PooledConnectionFactory;
    import org.apache.kafka.clients.producer.ProducerConfig;
    import org.apache.kafka.common.serialization.StringSerializer;
    import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    import org.springframework.kafka.annotation.EnableKafka;
    import org.springframework.kafka.core.DefaultKafkaProducerFactory;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.kafka.core.ProducerFactory;
    import org.springframework.scheduling.annotation.EnableAsync;
    import org.springframework.web.client.RestTemplate;

    import java.util.HashMap;
    import java.util.Map;

    @Configuration
    @EnableAsync
    @EnableEurekaClient
    @EnableKafka
    public class EntryPointConfig {

        @Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }



        @Bean
        public ProducerFactory<String, String> producerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Replace with your Kafka server address
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, String> kafkaTemplate() {
            return new KafkaTemplate<>(producerFactory());
        }






    }



