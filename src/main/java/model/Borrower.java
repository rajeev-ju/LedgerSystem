package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Borrower {
    private final String id;
    private final String name;
    private List<Loan> loans;

    public Borrower(@NonNull final String id, @NonNull final String name) {
        this.id = id;
        this.name = name;
        this.loans = new ArrayList<>();
    }
}
