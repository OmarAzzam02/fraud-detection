package com.omarazzam.paymentguard.evaluation.service;



import com.omarazzam.paymentguard.evaluation.entity.senario.UserSenario;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
public class UserSenarioCashe {

    @Autowired
     List<UserSenario> cashe; // this cashe this could is only a prototype

    public void addSenarioToCashe(UserSenario senario) {
         log.info("adding to Cashe");
         cashe.add(senario);
         log.info(cashe.size());

    }

    public void addCollectionOfScenariosToCashe(List<UserSenario> collection) {
        log.info("adding collection to Cashe");
        cashe.addAll(collection);
        log.info(cashe.size());
        // add the data from the user scinario database into this cashe
    }

    List<UserSenario> getCashe() {
        return cashe;
    }


}
