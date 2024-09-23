package com.omarazzam.paymentguard.evaluation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.evaluation.entity.message.FraudStatus;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;

import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class FraudCheckProcessor {

    private static final String READER_URL = "http://ENTRYPOINT/api/v1/fraud-status";
    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    private EvaluateMessageService evaluateMessageService;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    KafkaTemplate<String , Object> kafkaTemplate;

    @Autowired
    private UserSenarioCashe cache;
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    void buildPipeline(final StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("transaction", Consumed.with(STRING_SERDE, STRING_SERDE));

        messageStream.foreach((key, value) -> {
            try {

                log.info("kafka stream");
                PaymentTransactionEvaluation message = objectMapper.readValue(value, PaymentTransactionEvaluation.class);


                boolean res = cache.getCashe()
                        .parallelStream()
                        .anyMatch(scenario -> evaluateMessageService
                        .evaluate(message, scenario));

                FraudStatus flag = res ? FraudStatus.FRAUD : FraudStatus.NOTFRAUD;
                message.setFlag(flag);


                sendToReaderWithProducer(message);

            } catch (Exception e) {
                log.error("Error processing message", e);

            }
        });
    }




    @Async
    public void sendToReaderWithProducer(final PaymentTransactionEvaluation message) {

        kafkaTemplate.send("evaluated-transactions" , message);

    }


    @Async
    public CompletableFuture<Void> sendToReader(final PaymentTransactionEvaluation message) {
        return CompletableFuture.runAsync(() -> {
            try {

                log.info("Sending message to reader: {}", message);
                ResponseEntity<Void> response = restTemplate.postForEntity(READER_URL, message, Void.class);
                log.info("Response from reader: {}", response.getStatusCode());
            } catch (Exception e) {
                log.error("Failed to send message to reader", e);
                throw new RuntimeException("Failed to send message to reader", e);
            }
        });
    }

}
