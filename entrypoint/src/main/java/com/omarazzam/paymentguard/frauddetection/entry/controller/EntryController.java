package com.omarazzam.paymentguard.frauddetection.entry.controller;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import com.omarazzam.paymentguard.frauddetection.entry.exception.LicenseIsNotValidException;
import com.omarazzam.paymentguard.frauddetection.entry.exception.NoServiceInstanceFoundException;
import com.omarazzam.paymentguard.frauddetection.entry.service.EvaluatedMessageCashe;
import com.omarazzam.paymentguard.frauddetection.entry.service.MonitorService;
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

    @Autowired
    MonitorService monitorService;

    @Autowired
    EvaluatedMessageCashe evaluatedMessageCashe;


    @PostMapping("/entry-point")
    public ResponseEntity<?> getEntryPoint(@RequestBody PaymentTransaction message) {
        try {

            return ResponseEntity.ok(requestHandler.HandleValidatorRequest(message));
        }catch (NoServiceInstanceFoundException e ) {
            log.error(e.getMessage());
               return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            log.error(e.getMessage());
               return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/fraud-status")
    public ResponseEntity<Void> messageStatus(@RequestBody PaymentTransaction message) {
        log.info("IN controller frauad status update");

        synchronized (monitorService.getSharedMonitor()) {
            evaluatedMessageCashe.addTransaction(message);
            monitorService.getSharedMonitor().notifyAll();
        }
    return ResponseEntity.ok().build();
    }
}
