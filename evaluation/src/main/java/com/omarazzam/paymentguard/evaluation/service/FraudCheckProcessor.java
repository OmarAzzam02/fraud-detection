    package com.omarazzam.paymentguard.evaluation.service;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
    import com.omarazzam.paymentguard.evaluation.entity.message.FraudStatus;
    import lombok.extern.log4j.Log4j2;
    import org.apache.kafka.common.serialization.Serde;
    import org.apache.kafka.common.serialization.Serdes;
    import org.apache.kafka.streams.StreamsBuilder;
    import org.apache.kafka.streams.kstream.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cloud.client.ServiceInstance;
    import org.springframework.cloud.client.discovery.DiscoveryClient;
    import org.springframework.scheduling.annotation.Async;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.util.List;
    import java.util.concurrent.CompletableFuture;

    @Service
    @Log4j2
    public class FraudCheckProcessor {

        private static final String  READER_URL =  "http://ENTRYPOINT/api/v1/fraud-status";
        private static final Serde<String> STRING_SERDE = Serdes.String();

        @Autowired
        private EvaluateMessageService evaluateMessageService;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private UserSenarioCashe cashe;
        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private DiscoveryClient discoveryClient;

        @Autowired
        void buildPipeline(StreamsBuilder streamsBuilder) {
            KStream<String, String> messageStream = streamsBuilder
                    .stream("transaction", Consumed.with(STRING_SERDE, STRING_SERDE));

            messageStream.foreach((key, value) -> {
                try {

                    log.info("IN kafka Stream ");
                    PaymentTransactionEvaluation message = objectMapper.readValue(value, PaymentTransactionEvaluation.class);



                    boolean res = cashe.getCashe()
                            .parallelStream()
                            .anyMatch(scenario -> evaluateMessageService.evaluate(message, scenario));

                    FraudStatus flag = res ? FraudStatus.FRAUD : FraudStatus.NOTFRAUD;
                    message.setFlag(flag);
                    long endTime = System.currentTimeMillis();

                    sendToReader(message);




                } catch (Exception e) {
                    log.error("Error processing message", e);

                }
            });
        }




        public CompletableFuture<Void> sendToReader(PaymentTransactionEvaluation message) {
            return CompletableFuture.runAsync(() -> {
                try {

                    String url = "http://ENTRYPOINT//api/v1/fraud-status";
                    restTemplate.postForEntity(url, message, String.class);
                } catch (Exception e) {

                    throw new RuntimeException("Failed to send message to reader", e);
                }
            });
        }
    }
