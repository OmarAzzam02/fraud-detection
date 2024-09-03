package com.omarazzam.paymentguard.evaluation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.omarazzam.paymentguard.evaluation.entity.condition.FullCondition;
import com.omarazzam.paymentguard.evaluation.entity.connector.AndConnector;
import com.omarazzam.paymentguard.evaluation.entity.connector.Connector;
import com.omarazzam.paymentguard.evaluation.entity.connector.OrConnector;
import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionDLL;
import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EvaluateMessageService {
    @Autowired
    private UserSenarioCashe cache;

    @Autowired
    ObjectMapper objectMapper;


    public void evaluateMessage(PaymentTransactionEvaluation message) {
        log.info("Evaluate message");

        boolean isFraudulent = cache.getCashe().parallelStream()
                .anyMatch(scenario -> evaluate(message, scenario));

        if (isFraudulent) {
           log.info("Message is fraud  {} " , message.getId());
           //save to DB ASync
        }
     else   log.info("Not Fraud");

    }


    @Async
    public boolean evaluate(PaymentTransactionEvaluation message, UnifiedConditionDLL scenario) {

        UnifiedConditionNode curr = scenario.getHead();
        boolean finalResult = false;
        while (curr != null) {

            try {
                FullCondition condition = curr.getData().getFullCondition();
                Object messageFieldValue = getMessageValue(message, condition.getCondition().getField());
                boolean conditionResult = condition.getCondition().evaluate(messageFieldValue);
               // boolean skip = handleConnectorLogic(conditionResult, condition.getConnector());
                if (curr.getData() == scenario.getHead().getData())
                    finalResult = conditionResult;
                else
                    finalResult = condition.getConnector().evaluate(finalResult, conditionResult);


                curr = curr.getNext();
            } catch (Exception ex) {
                log.error(ex);
            }
        }

        return finalResult;
    }


    private Object getMessageValue(PaymentTransactionEvaluation message, String fieldToSearch) throws JsonProcessingException {
        String messageJson = objectMapper.writeValueAsString(message);
        Object value = JsonPath.read(messageJson, fieldToSearch);

        return value;
    }


    private boolean handleConnectorLogic(boolean conditionResult, Connector connector) {
        return (!conditionResult && connector instanceof AndConnector) ||
                (conditionResult && connector instanceof OrConnector);
    }


}