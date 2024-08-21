package com.omarazzam.paymentguard.evaluation.service;

import com.omarazzam.paymentguard.evaluation.entity.message.PaymentTransactionEvaluation;
import com.omarazzam.paymentguard.evaluation.entity.senario.ConditionConnector;
import com.omarazzam.paymentguard.evaluation.entity.senario.ConditionDetails;
import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;
import com.omarazzam.paymentguard.evaluation.service.testsenario.ParseConditionService;
import com.omarazzam.paymentguard.evaluation.service.testsenario.connector.Connector;
import com.omarazzam.paymentguard.evaluation.service.testsenario.connector.ConnectorFactory;
import com.omarazzam.paymentguard.evaluation.service.testsenario.operator.Operator;
import com.omarazzam.paymentguard.evaluation.service.testsenario.operator.OperatorFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EvaluateMessageService     {

    @Autowired
    private UserSenarioCashe cache;

    @Autowired
    private ParseConditionService parseConditionService;

    @Autowired
    private ConnectorFactory connectorFactory;

    @Autowired
    private OperatorFactory operatorFactory;

    public void evaluateMessage(PaymentTransactionEvaluation message) {
        boolean scenarioMatched = false;


        for (UserSenario scenario : cache.getCashe()) {
            boolean scenarioEvaluationResult = evaluateScenario(scenario, message);

            if (scenarioEvaluationResult) {
                scenarioMatched = true;
                break;
            }
        }

        if (scenarioMatched) {
            log.info("Transaction needs to be locked. must save in the database");
        } else {
            log.info("Transaction evaluated, no scenario applied. OK NO Fraudoooo");
        }
    }

    private boolean evaluateScenario(UserSenario scenario, PaymentTransactionEvaluation message) {
        boolean prevResult = false;

        ConditionDetails firstCondition = findConditionById(scenario, scenario.getCondition().getConnectors().get(0).getLeftId());
        prevResult = evaluateCondition(firstCondition, message);

        for (ConditionConnector connector : scenario.getCondition().getConnectors()) {
            ConditionDetails rightCondition = findConditionById(scenario, connector.getRightId());

            boolean rightConditionResult = evaluateCondition(rightCondition, message);
            Connector connectorLogic = connectorFactory.createConnector(connector.getConnector().trim());

            prevResult = connectorLogic.evaluate(prevResult, rightConditionResult);
        }

        return prevResult;
    }

    private ConditionDetails findConditionById(UserSenario scenario, int conditionId) {
        return scenario.getCondition().getDetails().stream()
                .filter(cond -> cond.getConditionId() == conditionId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Condition not found for ID: " + conditionId));
    }

    private boolean evaluateCondition(ConditionDetails condition, PaymentTransactionEvaluation message) {
        Operator operator = operatorFactory.createOperator(condition.getConditionOperator().toString().trim());
        return parseConditionService.parseConditionThenEvaluate(operator, condition, message);
    }
}
