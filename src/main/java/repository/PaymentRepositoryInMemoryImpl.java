package repository;

import lombok.NonNull;
import model.Bank;
import model.Borrower;
import model.Loan;
import model.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepositoryInMemoryImpl implements IPaymentRepository {
    private final Map<Borrower, List<Payment>> paymentToBorrowerMap;

    public PaymentRepositoryInMemoryImpl() {
        this.paymentToBorrowerMap = new HashMap<>();
    }


    @Override
    public Payment createPayment(@NonNull Bank bank, @NonNull Borrower borrower, @NonNull Loan loan, @NonNull int amount, int emiNumber) {
        Payment newPayment = new Payment(bank, borrower, loan, amount, emiNumber);
        try {
            if(paymentToBorrowerMap.containsKey(borrower))
                this.paymentToBorrowerMap.get(borrower).add(newPayment);
            else{
                List<Payment> paymentList = new ArrayList<>();
                paymentList.add(newPayment);
                paymentToBorrowerMap.put(borrower, paymentList);
            }
            return newPayment;
        }catch (Exception e){
            System.out.println("There is some issue with fetching map details");
        }
        return null;
    }

    @Override
    public List<Payment> getPayments(@NonNull Borrower borrower) {
        if(!this.paymentToBorrowerMap.containsKey(borrower))
            throw new RuntimeException("Borrower doesn't exist in the database. Please enter correct Borrower name");

        return this.paymentToBorrowerMap.get(borrower);
    }
}
