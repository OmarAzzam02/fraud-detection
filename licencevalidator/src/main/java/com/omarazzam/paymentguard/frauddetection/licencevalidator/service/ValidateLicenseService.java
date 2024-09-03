package com.omarazzam.paymentguard.frauddetection.licencevalidator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.Licence;
import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.ValidRefrence;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
public class ValidateLicenseService {

    private List<ValidRefrence> validReferences;

    @Autowired
    private  ObjectMapper objectMapper;



    private void readFile() {
        try {
            // Reading the JSON as a list of ValidRefrence objects
            ClassPathResource resource = new ClassPathResource("data.json");
            validReferences = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<ValidRefrence>>() {});
            log.info("Valid references loaded: {}", validReferences);
        } catch (IOException e) {
            log.error("Error reading JSON file", e);
        }
    }

    public boolean isValidLicense(Licence message) throws Exception {
        if (message == null || message.getPayType() == null || message.getReferenceNumber() == null || message.getReferenceNumber().length() < 4) {
            throw new Exception("Invalid message or missing data");
        }

        readFile();
        String payType = message.getPayType().toString();
        String referencePrefix = message.getReferenceNumber().substring(0, 4);


        return validReferences.stream()
                .filter(validRef -> validRef.getPayType().toString().equalsIgnoreCase(payType))
                .flatMap(validRef -> validRef.getReff().stream())
                .anyMatch(referencePrefix::equalsIgnoreCase);
    }
}
