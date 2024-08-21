package com.omarazzam.paymentguard.frauddetection.scenariomanager.dao;

import com.omarazzam.paymentguard.frauddetection.scenariomanager.dto.UserSenario;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Repository
public class UserScenarioDAOImpl implements UserScenarioDAO {

    public void insertScenario(UserSenario scenarioMap) {
        try {
            log.info("Inserting user scenario in the database");
            log.info(scenarioMap);
              //  userScenarioDAO.save(scenarioMap);

        }catch (Exception e) {
            log.error(e);
        }
    }
}
