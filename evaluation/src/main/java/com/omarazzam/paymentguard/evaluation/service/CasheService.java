package com.omarazzam.paymentguard.evaluation.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@Log4j2
public class CasheService {

    @Autowired
    List<Map<String, ?>> cashe; // this cashe this could is only a prototype

    public void addSenarioToCashe(Map<String, ?> senario) {

         log.info("adding to Cashe");
         // if the scenario name exists in the cashe update it override it



    }


}
