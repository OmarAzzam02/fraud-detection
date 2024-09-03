package com.omarazzam.paymentguard.evaluation.service;

import com.omarazzam.paymentguard.evaluation.entity.scenario.UnifiedConditionDLL;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
public class UserSenarioCashe {

    @Autowired
    List<UnifiedConditionDLL> cashe;


    public void addCollectionOfScenariosToCashe(List<UnifiedConditionDLL> collection) {
        log.info("adding collection to Cashe");
        log.info(collection.toString());
        cashe.addAll(collection);
        log.info(cashe.size());
    }

    List<UnifiedConditionDLL> getCashe() {
        return cashe;
    }
}
