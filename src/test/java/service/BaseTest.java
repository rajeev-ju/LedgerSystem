package service;

import org.junit.Before;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import repository.*;

public class BaseTest {
    @Mock
    protected IPaymentRepository iPaymentRepository;

    @Mock
    protected ILoanRepository iLoanRepository;

    @Mock
    protected IBorrowerRepository iBorrowerRepository;

    @Before
    public void setup() {

    }

    @BeforeEach
    public void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

}
