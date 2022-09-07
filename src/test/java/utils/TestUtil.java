package utils;

import model.Bank;
import model.Borrower;
import model.Loan;
import model.Payment;

public class TestUtil {
    public static Borrower getBorrowerObj(String name){
        return new Borrower("1", name);
    }

    public static Bank getBankObj(String bankName){
        return new Bank(bankName);
    }

    public static Loan getLoanObject(){
        Borrower borrower = getBorrowerObj("Dale");
        Bank bank = getBankObj("MBI");
        Loan newLoan = new Loan("1234", 10000, 4.0, borrower, bank, 5);
        return newLoan;
    }

    public static Payment getPaymentObj(){
        Payment payment = new Payment(getBankObj("MBI"), getBorrowerObj("Dale"), getLoanObject(), 1000, 5);
        return payment;
    }
}
