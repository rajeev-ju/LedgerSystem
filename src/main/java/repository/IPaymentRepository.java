package repository;

import lombok.NonNull;
import model.Bank;
import model.Borrower;
import model.Loan;
import model.Payment;

import java.util.List;

public interface IPaymentRepository {
    Payment createPayment(@NonNull Bank bank, @NonNull Borrower borrower, @NonNull Loan loan, @NonNull int amount, int emiNumber);
    List<Payment> getPayments(@NonNull Borrower borrower);
}
