package service;

import lombok.NonNull;
import model.Loan;

public interface ILoanService {
    Loan createLoan(@NonNull String bankName, @NonNull final String borrowerName, @NonNull final int principalAmount, @NonNull final int loanPeriod, @NonNull final double rateOfInterest);
}
