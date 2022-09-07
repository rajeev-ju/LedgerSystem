package repository;

import lombok.NonNull;
import model.Borrower;

import java.util.ArrayList;
import java.util.List;

public class BorrowerRepositoryInMemoryImpl implements IBorrowerRepository {
    private final List<Borrower> borrowers;

    public BorrowerRepositoryInMemoryImpl() {
        this.borrowers = new ArrayList<>();
    }

    @Override
    public Borrower getBorrower(@NonNull String name) {
        for(Borrower borrower : borrowers)
            if(borrower.getName().equals(name))
                return borrower;

        return null;
    }

    @Override
    public Borrower createBorrower(@NonNull String id, @NonNull String name) {
        for(Borrower borrower : borrowers)
            if(borrower.getName().equals(name) && borrower.getId().equals(id))
                return borrower;

        Borrower newBorrower = new Borrower(id, name);
        this.borrowers.add(newBorrower);
        return newBorrower;
    }
}
