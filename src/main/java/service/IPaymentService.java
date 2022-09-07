package service;

import lombok.NonNull;
import model.Payment;

public interface IPaymentService {
    Payment recordPayment(@NonNull String bankName, @NonNull String borrowerName, @NonNull int lumpSumAmount, @NonNull int numberOfEMIsPaid);
}
