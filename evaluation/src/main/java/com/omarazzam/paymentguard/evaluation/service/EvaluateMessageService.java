package com.omarazzam.paymentguard.evaluation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.evaluation.entity.condition.FullCondition;
import com.omarazzam.paymentguard.evaluation.entity.connector.AndConnector;
import com.omarazzam.paymentguard.evaluation.entity.connector.Connector;
import com.omarazzam.paymentguard.evaluation.entity.connector.OrConnector;
import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionDLL;
import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionNode;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class EvaluateMessageService {
    @Autowired
    private UserScenarioCache cache;

    @Autowired
   private ObjectMapper objectMapper;


    private static final Map<Class<?>, Map<String, Field>> fieldCache = new HashMap<>();



    public boolean evaluate(PaymentTransaction message, UnifiedConditionDLL scenario) {

        UnifiedConditionNode curr = scenario.getHead();
        boolean finalResult = false;


         FullCondition headCondition = curr.getData().getFullCondition();
         Object headMessageFieldValue = getMessageValue(message, headCondition.getCondition().getField());
         finalResult = headCondition.getCondition().evaluate(headMessageFieldValue);
         curr = curr.getNext();

         //log.info("Evaluate {} " , curr.getData().getScenarioName());
        while (curr != null) {

            try {

                FullCondition condition = curr.getData().getFullCondition();
                Object messageFieldValue = getMessageValue(message, condition.getCondition().getField());
                boolean conditionResult = condition.getCondition().evaluate(messageFieldValue);
                finalResult = condition.getConnector().evaluate(finalResult, conditionResult);

                curr = curr.getNext();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    return finalResult;
    }



    private Object getMessageValue(PaymentTransaction message, String fieldToSearch) {
        try {
            String[] fields = fieldToSearch.split("\\.");
            Object currentObject = message;

            for (String fieldName : fields) {
                Class<?> currentClass = currentObject.getClass();


                Map<String, Field> classFieldMap = fieldCache.computeIfAbsent(currentClass, k -> new HashMap<>());


                Field field = classFieldMap.computeIfAbsent(fieldName, f -> {
                    try {
                        Field declaredField = currentClass.getDeclaredField(f);
                        declaredField.setAccessible(true);
                        return declaredField;
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException("Field not found: " + f, e);
                    }
                });


                currentObject = field.get(currentObject);
            }
            return currentObject;
        } catch (Exception e) {
            throw new RuntimeException("Error accessing field: " + fieldToSearch, e);
        }
    }

    private boolean handleConnectorLogic(boolean conditionResult, Connector connector) {
        return (!conditionResult && connector instanceof AndConnector) ||
                (conditionResult && connector instanceof OrConnector);
    }
}