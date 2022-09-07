package service;

import lombok.NonNull;
import model.Bank;
import model.Borrower;
import model.Loan;
import repository.IBorrowerRepository;
import repository.ILoanRepository;

import java.util.UUID;

public class LoanServiceImpl implements ILoanService{
    private final ILoanRepository loanRepository;
    private final IBorrowerRepository borrowerRepository;

    public LoanServiceImpl(@NonNull final ILoanRepository loanRepository, @NonNull final IBorrowerRepository borrowerRepository) {
        this.loanRepository = loanRepository;
        this.borrowerRepository = borrowerRepository;
    }

    /**
     * @param bankName :- Name of the bank who has provided the loan
     * @param borrowerName :- Name of the borrower
     * @param principalAmount :- The principal amount which has given to borrower in the form of loan
     * @param loanPeriod :- Total period for which loan is taken
     * @param rateOfInterest :- Rate of interest on the loan
     * @return loan :- It consists of all the details about the loan, borrower, bank etc.
     */
    @Override
    public Loan createLoan(@NonNull String bankName, @NonNull final String borrowerName, @NonNull final int principalAmount, @NonNull final int loanPeriod, @NonNull final double rateOfInterest){
        Borrower borrower = (borrowerRepository.getBorrower(borrowerName) != null) ? borrowerRepository.getBorrower(borrowerName) : borrowerRepository.createBorrower(UUID.randomUUID().toString(), borrowerName);
        Bank bank = new Bank(bankName);
        Loan newLoan = loanRepository.createLoan(UUID.randomUUID().toString(), principalAmount, rateOfInterest, borrower, bank, loanPeriod);

        return newLoan;
    }

}
