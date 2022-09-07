package service;

import exception.BorrowerNotFoundException;
import exception.LoanNotFoundException;
import lombok.NonNull;
import model.Borrower;
import model.Loan;
import model.Payment;
import repository.IBorrowerRepository;
import repository.ILoanRepository;
import repository.IPaymentRepository;

public class PaymentServiceImpl implements IPaymentService{
    private final IPaymentRepository paymentRepository;
    private final ILoanRepository loanRepository;
    private final IBorrowerRepository borrowerRepository;


    public PaymentServiceImpl(@NonNull IPaymentRepository paymentRepository, @NonNull ILoanRepository loanRepository, @NonNull IBorrowerRepository borrowerRepository) {
        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;
        this.borrowerRepository = borrowerRepository;
    }

    /**
     * It records the payment of the lumpsum amount towards a loan.
     * @param bankName :- Name of the bank who has provided the loan
     * @param borrowerName :- Name of the borrower
     * @param lumpSumAmount :- Lumpsum amount to be paid for the loan
     * @param numberOfEMIsPaid :- after which emi this lumpsum amount needs to be paid
     */
    @Override
    public Payment recordPayment(@NonNull String bankName, @NonNull String borrowerName, @NonNull int lumpSumAmount, @NonNull int numberOfEMIsPaid){
        try {
            if (borrowerRepository.getBorrower(borrowerName) == null)
                throw new BorrowerNotFoundException("Borrower is not in the system. Please add him");
            Borrower borrower = borrowerRepository.getBorrower(borrowerName);

            if (loanRepository.getLoan(borrower, bankName) == null)
                throw new LoanNotFoundException("There is no loan with this borrower in this bank");

            Loan loan = loanRepository.getLoan(borrower, bankName);
            Payment payment = paymentRepository.createPayment(loan.getBank(), borrower, loan, lumpSumAmount, numberOfEMIsPaid);
            return payment;
        }catch (BorrowerNotFoundException ex){
            System.out.println("Exception occurred : " + ex);
        }catch (LoanNotFoundException ex){
            System.out.println("Exception occurred : " + ex);
        }
        return null;
    }
}
