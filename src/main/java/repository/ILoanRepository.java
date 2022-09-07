package repository;

import lombok.NonNull;
import model.Bank;
import model.Borrower;
import model.Loan;

public interface ILoanRepository {
    Loan createLoan(@NonNull String id, @NonNull int principalAmount, @NonNull Double rateOfInterest, @NonNull Borrower borrower, @NonNull Bank bank, @NonNull int loanPeriod);
    Loan getLoan(@NonNull Borrower borrower, @NonNull String bankName);
}
