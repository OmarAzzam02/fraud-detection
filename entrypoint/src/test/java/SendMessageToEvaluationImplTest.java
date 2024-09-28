import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarazzam.paymentguard.frauddetection.entry.entity.PaymentTransaction;
import com.omarazzam.paymentguard.frauddetection.entry.service.EvaluatedMessageCashe;
import com.omarazzam.paymentguard.frauddetection.entry.service.MonitorService;
import com.omarazzam.paymentguard.frauddetection.entry.service.SendMessageToEvaluationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SendMessageToEvaluationImplTest {

    @InjectMocks
    private SendMessageToEvaluationImpl sendMessageToEvaluation;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;



    @Mock
    private EvaluatedMessageCashe evaluatedMessageCashe;

    @Mock
    private MonitorService monitorService;

    @Mock
    private Acknowledgment acknowledgment;

    private PaymentTransaction paymentTransaction;

    @BeforeEach
    public void setUp() {
        paymentTransaction = new PaymentTransaction();
        paymentTransaction.setId(4848498);
    }

    @Test
    public void testSendMessage() throws InterruptedException {
        when(evaluatedMessageCashe.isResponeBack(paymentTransaction.getId())).thenReturn(true);
        when(evaluatedMessageCashe.getEvaluatedMessages().get(paymentTransaction.getId())).thenReturn(paymentTransaction);

        PaymentTransaction result = sendMessageToEvaluation.sendMessage(paymentTransaction);

        verify(kafkaTemplate, times(1)).send("transaction", paymentTransaction);
        verify(evaluatedMessageCashe, times(1)).removeTransaction(paymentTransaction);
        assertEquals(paymentTransaction, result);
    }

    @Test
    public void testRecieveEvaluatedMessage() {
        doNothing().when(evaluatedMessageCashe).addTransaction(paymentTransaction);
        doNothing().when(acknowledgment).acknowledge();

        sendMessageToEvaluation.recieveEvaluatedMessage(paymentTransaction, acknowledgment);

        verify(evaluatedMessageCashe, times(1)).addTransaction(paymentTransaction);
        verify(acknowledgment, times(1)).acknowledge();
    }
}