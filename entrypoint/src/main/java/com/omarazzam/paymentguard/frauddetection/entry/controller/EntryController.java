package com.omarazzam.paymentguard.frauddetection.entry.controller;
import com.omarazzam.paymentguard.frauddetection.entry.exception.LicenseIsNotValidException;
import com.omarazzam.paymentguard.frauddetection.entry.exception.NoServiceInstanceFoundException;
import com.omarazzam.paymentguard.frauddetection.entry.service.RequestHandlerImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/v1")
public class EntryController {

    @Autowired
    RequestHandlerImpl requestHandler;

    @PostMapping("/entry-point")
    public ResponseEntity<?> getEntryPoint(@RequestBody String message) {
        try {
            requestHandler.HandleValidatorRequest(message);
               return ResponseEntity.ok("Valid License");
        }catch (NoServiceInstanceFoundException e ) {
            log.error(e.getMessage());
               return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            log.error(e.getMessage());
               return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
