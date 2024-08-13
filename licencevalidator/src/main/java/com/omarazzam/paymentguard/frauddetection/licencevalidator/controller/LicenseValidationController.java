package com.omarazzam.paymentguard.frauddetection.licencevalidator.controller;


import com.omarazzam.paymentguard.frauddetection.licencevalidator.entity.Licence;
import com.omarazzam.paymentguard.frauddetection.licencevalidator.service.ValidateLicenseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/license-service")
public class LicenseValidationController {

@Autowired
    ValidateLicenseService validateLicenseService;

    @PostMapping("/validate")
    ResponseEntity<?> validateMessage( @RequestBody Licence message) {


        try {
        boolean isValid = validateLicenseService.isValidLicense(message);

        if (isValid)
            return ResponseEntity.ok().body("Valid License");

        else return ResponseEntity.badRequest().body("Invalid License");

        }catch (Exception ex){
            log.error(ex);
            return ResponseEntity.badRequest().body("An Expected Error occurred");
        }


    }

}
