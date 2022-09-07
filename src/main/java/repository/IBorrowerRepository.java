package repository;

import lombok.NonNull;
import model.Borrower;

public interface IBorrowerRepository {
    Borrower getBorrower(@NonNull String name);
    Borrower createBorrower(@NonNull String id, @NonNull String name);
}
