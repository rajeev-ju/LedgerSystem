package service;

import lombok.NonNull;

public interface IBalanceService {
    int getBalance(@NonNull String bankName, @NonNull String borrowerName, @NonNull int numberOfEMIsPaid);
}
