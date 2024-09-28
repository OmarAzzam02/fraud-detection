package com.omarazzam.paymentguard.evaluation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.util.PaymentTransactionSerde;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import com.omarazzam.paymentguard.frauddetection.entry.entity.FraudStatus;

import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Log4j2
public class FraudCheckProcessor {

    private static final String READER_URL = "http://ENTRYPOINT/api/v1/fraud-status";
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final PaymentTransactionSerde PAYMENT_TRANSACTION_SERDE = new PaymentTransactionSerde();

    @Autowired
    private EvaluateMessageService evaluateMessageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private UserScenarioCache cache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    void buildPipeline(final StreamsBuilder streamsBuilder) {
        streamsBuilder
                .stream("transaction", Consumed.with(STRING_SERDE, PAYMENT_TRANSACTION_SERDE))
                .mapValues(this::processTransaction)
                .filter((key, value) -> value != null);

    }

    private PaymentTransaction processTransaction(PaymentTransaction transaction) {
        try {
            log.info("Processing Kafka stream message...");


            boolean isFraud = cache.getCashe()
                    .parallelStream()
                    .anyMatch(scenario -> evaluateMessageService.evaluate(transaction, scenario));

            transaction.setFlag(isFraud ? FraudStatus.FRAUD : FraudStatus.NOTFRAUD);

            sendToReaderWithProducer(transaction);

            return transaction;
        } catch (Exception e) {
            log.error("Error processing message", e);
            return null;
        }
    }

    public void sendToReaderWithProducer(final PaymentTransaction message) {
        kafkaTemplate.send("evaluated-transactions", message);
    }
}
