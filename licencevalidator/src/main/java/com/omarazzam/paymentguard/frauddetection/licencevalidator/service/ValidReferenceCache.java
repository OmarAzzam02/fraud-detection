package com.omarazzam.paymentguard.frauddetection.licencevalidator.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.ValidRefrence;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Log4j2
@Service
@Getter
public class ValidReferenceCache {

    @Autowired
    private List<ValidRefrence> validRefrenceCashe;

    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    void init() {


        try {
        ClassPathResource resource = new ClassPathResource("data.json");
            validRefrenceCashe = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<ValidRefrence>>() {
            });

            log.info("ValidRefrenceCashe: {}", validRefrenceCashe.size());

        }catch (Exception e){
           log.info("Somthing went wrong readnig the  License Reff JSON file");
        }



    }


    public List<ValidRefrence> getValidRefrences() {
        return validRefrenceCashe;
    }


}
