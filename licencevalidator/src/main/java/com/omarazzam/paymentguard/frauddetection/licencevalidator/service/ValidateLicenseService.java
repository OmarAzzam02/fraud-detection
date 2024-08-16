package com.omarazzam.paymentguard.frauddetection.licencevalidator.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.Licence;
import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.PayType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;


@Log4j2
@Service
public class ValidateLicenseService {



    private Map<String, List<String>> validLicenses;

    @Autowired
    private final ObjectMapper objectMapper;

    public ValidateLicenseService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


        private void readFile() {
            try {

                ClassPathResource resource = new ClassPathResource("data.json");
                Map<String, List<String>> temp = objectMapper.readValue(resource.getInputStream(), new TypeReference<Map<String, List<String>>>() {});
                validLicenses = Collections.unmodifiableMap(temp);
                log.info("Valid licenses loaded: {}", validLicenses);
            } catch (IOException e) {
                log.error("Error reading JSON file", e);
            }
        }


    public boolean isValidLicense(Licence message) throws Exception {

        if (message == null)
            throw new Exception("Message cant be Empty");


        readFile();
        String payType = message.getPayType().toString();

        for (Map.Entry<String, List<String>> entry : validLicenses.entrySet()) {
            String key = entry.getKey();
            List<String> validPrefixes = entry.getValue();
            if (payType != null && payType.toUpperCase().startsWith(key)) {
                for (String prefix : validPrefixes) {
                    if (payType.length() >= 4 && message.getReferenceNumber().substring(0, 4).startsWith(prefix)) {
                        return true;
                    }
                }
            }
        }

        return false;


    }

}
