package repository;

import lombok.NonNull;
import model.Bank;
import model.Borrower;
import model.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanRepositoryInMemoryImpl implements ILoanRepository {
    private final List<Loan> loanList;

    public LoanRepositoryInMemoryImpl() {
        this.loanList = new ArrayList<>();
    }

    @Override
    public Loan createLoan(@NonNull String id, @NonNull int principalAmount, @NonNull Double rateOfInterest, @NonNull Borrower borrower, @NonNull Bank bank, @NonNull int loanPeriod) {
        Loan newLoan = new Loan(id, principalAmount, rateOfInterest, borrower, bank, loanPeriod);
        this.loanList.add(newLoan);

        return newLoan;
    }

    @Override
    public Loan getLoan(@NonNull Borrower borrower, @NonNull String bankName) {
        for(Loan loan : this.loanList)
            if(loan.getBank().getName().equals(bankName) && loan.getBorrower().equals(borrower))
                return loan;

        return null;
    }
}
