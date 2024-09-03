package com.omarazzam.paymentguard.evaluation.service;


import com.omarazzam.paymentguard.evaluation.dto.scenarioDTO.ConditionDTO;
import com.omarazzam.paymentguard.evaluation.dto.scenarioDTO.UserScenarioDTO;
import com.omarazzam.paymentguard.evaluation.entity.condition.Condition;
import com.omarazzam.paymentguard.evaluation.entity.condition.ConditionFactory;
import com.omarazzam.paymentguard.evaluation.entity.condition.FullCondition;
import com.omarazzam.paymentguard.evaluation.entity.connector.Connector;
import com.omarazzam.paymentguard.evaluation.entity.connector.ConnectorFactory;
import com.omarazzam.paymentguard.evaluation.entity.operator.Operator;
import com.omarazzam.paymentguard.evaluation.entity.operator.OperatorFactory;
import com.omarazzam.paymentguard.evaluation.entity.scenario.*;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.operator.OperatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ConstructScenarioUnifiedCondition {

    @Autowired
    UserSenarioCashe userSenarioCashe;

    @Autowired
    OperatorFactory operatorFactory;

    @Autowired
    ConnectorFactory connectorFactory;

    @Autowired
    ConditionFactory conditionFactory;

    public void createUnifiedCondition(List<UserScenarioDTO> scenarios) {
            log.info("Creating unified condition");

            List<UnifiedConditionDLL>  finalScenarioCondition = new ArrayList<>();
        for (UserScenarioDTO scenario : scenarios) {
            UnifiedConditionDLL unifiedConditionList = new UnifiedConditionDLL();

            try {
                int i = 0;
                // Loop over the size of the condition details
                for (; i < scenario.getCondition().getDetails().size(); i++) {
                    Condition cond = createCondition(scenario.getCondition(), i);

                    Connector connector = null;
                    // Assign connector if it's available for the current index
                    if (i < scenario.getCondition().getConnectors().size()) {
                        String connect = scenario.getCondition().getConnectors().get(i).getConnector();
                        connector = connectorFactory.createConnector(connect);
                    }

                    else if (i == scenario.getCondition().getDetails().size() - 1 && i - 1 >= 0) {
                        String connect = scenario.getCondition().getConnectors().get(i - 1).getConnector();
                        connector = connectorFactory.createConnector(connect);
                    }

                    FullCondition fullCondition = FullCondition.builder()
                            .connector(connector)
                            .condition(cond)
                            .build();

                    UnifiedCondition uniCond = UnifiedCondition.builder()
                            .fullCondition(fullCondition)
                            .scenarioName(scenario.getName())
                            .payType(scenario.getSenarioType())
                            .build();

                    unifiedConditionList.add(uniCond);
                }
              finalScenarioCondition.add(unifiedConditionList);
            }  catch (Exception e) {
                log.error(e);
            }
        }
        log.info("Created unified condition: {} ", finalScenarioCondition);
        userSenarioCashe.addCollectionOfScenariosToCashe(finalScenarioCondition);
    }

    Condition createCondition(ConditionDTO conditionDTO, int i) throws OperatorException {

        Operator<? extends Operator> conditionOperator;
        String field = conditionDTO.getDetails().get(i).getConditionField();
        String type = conditionDTO.getDetails().get(i).getConditionType();
        String value = conditionDTO.getDetails().get(i).getConditionValue();
        String operator = conditionDTO.getDetails().get(i).getConditionOperator();
        conditionOperator = operatorFactory.createOperator(operator);

        return conditionFactory.createCondition(type, field, value, conditionOperator);
    }
}


