package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Loan {
    private final String id;
    private final int principalAmount;
    private final Double rateOfInterest;
    private final Borrower borrower;
    private final Bank bank;
    private final int loanPeriod;
    private final int emiAmount;
    private final int totalAmountToRepay;
    private int totalAmountPaidTillNow;
    private int noOfEMIsRemaining;
    private int noOfEMIsPaid;
    private final EMIPaymentType emiPaymentType = EMIPaymentType.MONTHLYEMI;
    private final List<Payment> lumpSumPaymentList;

    public Loan(String id, int principalAmount, Double rateOfInterest, Borrower borrower, Bank bank, int loanPeriod) {
        this.id = id;
        this.principalAmount = principalAmount;
        this.rateOfInterest = rateOfInterest;
        this.borrower = borrower;
        this.bank = bank;
        this.loanPeriod = loanPeriod;
        this.emiAmount = calculateEMIAmount(this.principalAmount, this.rateOfInterest, this.loanPeriod);
        this.totalAmountToRepay = calculateTotalAmountToRepay(this.principalAmount, this.rateOfInterest, this.loanPeriod);
        this.totalAmountPaidTillNow = 0;
        this.noOfEMIsRemaining = 12 * loanPeriod;
        this.noOfEMIsPaid = 0;
        this.lumpSumPaymentList = new ArrayList<>();
    }

    public int calculateEMIAmount(int principalAmount, double rateOfInterest, int loanPeriod){
        int totalInterest = (int)Math.ceil((principalAmount * rateOfInterest * loanPeriod) / (double)(100));
        int totalAmountToRepay = principalAmount + totalInterest;

        return (int)Math.ceil((double) totalAmountToRepay / ((double) loanPeriod * 12));
    }

    public int calculateTotalAmountToRepay(int principalAmount, double rateOfInterest, int loanPeriod){
        int totalInterest = (int)Math.ceil((principalAmount * rateOfInterest * loanPeriod) / (double)(100));

        return principalAmount + totalInterest;
    }

    public void addLumpSumPayment(Payment payment){
        this.lumpSumPaymentList.add(payment);
    }

    public int recordEMIPayment(int numberOfEMIsPaid){
        int amountPaid = numberOfEMIsPaid * this.getEmiAmount();
        int lumpsumAmount = 0;
        this.noOfEMIsPaid = numberOfEMIsPaid;
        if(lumpSumPaymentList.size() > 0)
            lumpsumAmount = recordLumpSumPayment(lumpSumPaymentList);

        amountPaid += lumpsumAmount;
        this.totalAmountPaidTillNow = amountPaid;
        return amountPaid;
    }

    public int recordLumpSumPayment(List<Payment>lumpSumPaymentList){
        int lumpsumAmount = 0;
        for(Payment payment : lumpSumPaymentList){
            if(payment.getToBePaidAfterThisEMI() <= this.noOfEMIsPaid){
                lumpsumAmount += payment.getAmount();
            }
        }
        return lumpsumAmount;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id='" + id + '\'' +
                ", principalAmount=" + principalAmount +
                ", rateOfInterest=" + rateOfInterest +
                ", borrower=" + borrower +
                ", bank=" + bank +
                ", loanPeriod=" + loanPeriod +
                ", emiAmount=" + emiAmount +
                ", totalAmountToRepay=" + totalAmountToRepay +
                ", noOfEMIsRemaining=" + noOfEMIsRemaining +
                ", emiPaymentType=" + emiPaymentType +
                '}';
    }
}
