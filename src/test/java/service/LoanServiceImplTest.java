package service;

import model.Loan;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import utils.TestUtil;

public class LoanServiceImplTest extends BaseTest{
    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    public void testCreateLoan(){
        Mockito.when(iBorrowerRepository.getBorrower(Mockito.anyString())).thenReturn(TestUtil.getBorrowerObj("Dale"));
        Mockito.when(iLoanRepository.createLoan(Mockito.anyString(), Mockito.anyInt(), Mockito.anyDouble(),Mockito.any(),Mockito.any(), Mockito.anyInt())).thenReturn(TestUtil.getLoanObject());

        Loan loan = loanService.createLoan("MBI", "Dale", 10000, 5, 4.0);

        Assert.assertNotNull(loan);
        Assert.assertEquals("Dale", loan.getBorrower().getName());
        Assert.assertEquals("MBI", loan.getBank().getName());
        Assert.assertEquals(10000, loan.getPrincipalAmount());
        Assert.assertEquals(5, loan.getLoanPeriod());
        Assert.assertEquals(new Double(4.0), loan.getRateOfInterest());
    }
}
