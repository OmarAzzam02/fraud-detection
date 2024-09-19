package com.omarazzam.paymentguard.frauddetection.licencevalidator.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ValidRefrence {
     private PayType payType;
     private List<String> reff;
}
