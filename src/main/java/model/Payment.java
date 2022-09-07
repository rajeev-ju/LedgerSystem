package model;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Payment {
    private final String id;
    private final int amount;
    private final Borrower borrower;
    private final Bank bank;
    private final Loan loan;
    private final int toBePaidAfterThisEMI;


    public Payment(@NonNull Bank bank, @NonNull Borrower borrower, @NonNull Loan loan, @NonNull int lumpSumAmount, @NonNull int emiNumber) {
        this.id = UUID.randomUUID().toString();
        this.amount = lumpSumAmount;
        this.borrower = borrower;
        this.bank = bank;
        this.loan = loan;
        this.toBePaidAfterThisEMI = emiNumber;
        recordPayment();
    }

    public void recordPayment() throws RuntimeException{
        if(loan.getTotalAmountToRepay() - this.getAmount() < 0 || this.toBePaidAfterThisEMI == 12 * loan.getLoanPeriod()){
            throw new RuntimeException("Loan is fully paid. No need to pay now");
        }

        loan.addLumpSumPayment(this);
    }
}
