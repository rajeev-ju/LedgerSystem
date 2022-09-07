package service;

import exception.BorrowerNotFoundException;
import exception.LoanNotFoundException;
import lombok.NonNull;
import model.Borrower;
import model.Loan;
import repository.IBorrowerRepository;
import repository.ILoanRepository;

public class BalanceServiceImpl implements IBalanceService{
    private final ILoanRepository loanRepository;
    private final IBorrowerRepository borrowerRepository;

    public BalanceServiceImpl(@NonNull final ILoanRepository loanRepository, @NonNull final IBorrowerRepository borrowerRepository) {
        this.loanRepository = loanRepository;
        this.borrowerRepository = borrowerRepository;
    }

    /**
     * It prints the total balance of a loan which needs to be paid by borrower to the bank
     * @param bankName :- Name of the bank who has provided the loan
     * @param borrowerName :- Name of the borrower
     * @param numberOfEMIsPaid :- Number of emis paid in total from the start
     */
    @Override
    public int getBalance(@NonNull String bankName, @NonNull String borrowerName, @NonNull int numberOfEMIsPaid){
        try {
            if (borrowerRepository.getBorrower(borrowerName) == null)
                throw new BorrowerNotFoundException("Borrower is not in the system. Please add him");
            Borrower borrower = borrowerRepository.getBorrower(borrowerName);

            if (loanRepository.getLoan(borrower, bankName) == null)
                throw new LoanNotFoundException("There is no loan with this borrower in this bank");

            Loan loan = loanRepository.getLoan(borrower, bankName);

            int amount_paid = loan.recordEMIPayment(numberOfEMIsPaid);

            int numberOfEMIsRemaining = (int) Math.ceil((double) (loan.getTotalAmountToRepay() - amount_paid) / (double) loan.getEmiAmount());
            loan.setNoOfEMIsRemaining(numberOfEMIsRemaining);

            System.out.println(bankName + " " + borrowerName + " " + amount_paid + " " + numberOfEMIsRemaining);
            return amount_paid;
        }catch (BorrowerNotFoundException ex){
            System.out.println("Exception occurred : " + ex);
        }catch (LoanNotFoundException ex){
            System.out.println("Exception occurred : " + ex);
        }
        return -1;
    }
}
