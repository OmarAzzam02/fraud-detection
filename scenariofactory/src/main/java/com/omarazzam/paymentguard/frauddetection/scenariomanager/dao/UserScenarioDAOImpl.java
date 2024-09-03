package com.omarazzam.paymentguard.frauddetection.scenariomanager.dao;

import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserScenario;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
public class UserScenarioDAOImpl implements UserScenarioDAO {

    public void insertScenario(UserScenario scenarioMap) {
        try {
            log.info("Inserting user scenario in the database");
            log.info(scenarioMap);
              //  userScenarioDAO.save(scenarioMap);

        }catch (Exception e) {
            log.error(e);
        }
    }
}
