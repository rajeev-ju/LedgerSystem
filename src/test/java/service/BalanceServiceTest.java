package service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import utils.TestUtil;

public class BalanceServiceTest extends BaseTest{
    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Test
    public void testGetBalance(){
        Mockito.when(iBorrowerRepository.getBorrower(Mockito.anyString())).thenReturn(TestUtil.getBorrowerObj("Dale"));
        Mockito.when(iLoanRepository.getLoan(Mockito.any(), Mockito.anyString())).thenReturn(TestUtil.getLoanObject());

        int amountPaid = balanceService.getBalance("MBI", "Dale", 5);
        Assert.assertEquals(1000, amountPaid);

    }
}
