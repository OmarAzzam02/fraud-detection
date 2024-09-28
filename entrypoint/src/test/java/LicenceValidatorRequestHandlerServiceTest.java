import com.omarazzam.paymentguard.frauddetection.entry.entity.LicenseDTO;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PayType;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import com.omarazzam.paymentguard.frauddetection.entry.service.LicenceValidatorRequestHandlerService;
import com.omarazzam.paymentguard.frauddetection.entry.service.SendMessageToEvaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class LicenceValidatorRequestHandlerServiceTest {

    @InjectMocks
    private LicenceValidatorRequestHandlerService licenceValidatorRequestHandlerService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SendMessageToEvaluation sendMessageToEvaluation;

    private PaymentTransaction paymentTransaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentTransaction = new PaymentTransaction();
        paymentTransaction.setId(44864848);
        paymentTransaction.setPayType(PayType.CARD);
        paymentTransaction.setReferenceNumber("Ref123");
    }



    @Test
    public void testSendRequestToLicenseValidator_Exception() {
        when(restTemplate.postForEntity(any(String.class), any(LicenseDTO.class), eq(String.class)))
                .thenThrow(new RuntimeException("Error"));

        try {
            licenceValidatorRequestHandlerService.sendRequestToLicenseValidator(paymentTransaction);
        } catch (Exception e) {
            assertEquals("Error", e.getMessage());
        }

        verify(restTemplate, times(1)).postForEntity(any(String.class), any(LicenseDTO.class), eq(String.class));
        verify(sendMessageToEvaluation, never()).sendMessage(any(PaymentTransaction.class));
    }
}