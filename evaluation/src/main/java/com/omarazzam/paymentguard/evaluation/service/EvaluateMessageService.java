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
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Log4j2
@Service
public class EvaluateMessageService {
    @Autowired
    private UserSenarioCashe cache;

    @Autowired
   private ObjectMapper objectMapper;





    public boolean evaluate(PaymentTransactionEvaluation message, UnifiedConditionDLL scenario) {

        UnifiedConditionNode curr = scenario.getHead();
        boolean finalResult = false;


         FullCondition headCondition = curr.getData().getFullCondition();
         Object headMessageFieldValue = getMessageValue(message, headCondition.getCondition().getField());
         finalResult = headCondition.getCondition().evaluate(headMessageFieldValue);
         curr = curr.getNext();

         log.info("Evaluate {} " , curr.getData().getScenarioName());
        while (curr != null) {

            try {

                FullCondition condition  = curr.getData().getFullCondition();
                Object messageFieldValue = getMessageValue(message, condition.getCondition().getField());
                boolean conditionResult  = condition.getCondition().evaluate(messageFieldValue);
                finalResult = condition.getConnector().evaluate(finalResult, conditionResult);

                curr = curr.getNext();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return finalResult;
    }



    private Object getMessageValue(PaymentTransactionEvaluation message, String fieldToSearch) {
        try {

            String[] fields = fieldToSearch.split("\\.");

            Object currentObject = message;


            for (String fieldName : fields) {

                Field field = currentObject.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);


                currentObject = field.get(currentObject);
            }


            return currentObject;

        } catch (Exception ex) {
            log.error("Error accessing field:  {}" , ex.getMessage());
            throw new RuntimeException("Error accessing field: " + ex.getMessage(), ex);
        }
    }



    private boolean handleConnectorLogic(boolean conditionResult, Connector connector) {
        return (!conditionResult && connector instanceof AndConnector) ||
                (conditionResult && connector instanceof OrConnector);
    }


}