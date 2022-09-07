package service;

import model.Payment;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import utils.TestUtil;

public class PaymentServiceImplTest extends BaseTest{

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Test
    public void testRecordPayment(){
        Mockito.when(iBorrowerRepository.getBorrower(Mockito.anyString())).thenReturn(TestUtil.getBorrowerObj("Dale"));
        Mockito.when(iLoanRepository.getLoan(Mockito.any(), Mockito.anyString())).thenReturn(TestUtil.getLoanObject());
        Mockito.when(iPaymentRepository.createPayment(Mockito.any(), Mockito.any(), Mockito.any(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(TestUtil.getPaymentObj());

        Payment actualPayment = paymentService.recordPayment("MBI", "DALE", 1000, 5);
        Assert.assertEquals(TestUtil.getPaymentObj().getToBePaidAfterThisEMI(), actualPayment.getToBePaidAfterThisEMI());
    }
}
