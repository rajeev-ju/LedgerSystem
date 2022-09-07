import lombok.NonNull;
import repository.*;
import service.BalanceServiceImpl;
import service.IPaymentService;
import service.LoanServiceImpl;
import service.PaymentServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LedgerSystem {
    private static final ILoanRepository loanRepository = new LoanRepositoryInMemoryImpl();
    private static final IBorrowerRepository borrowerRepository = new BorrowerRepositoryInMemoryImpl();
    private static final IPaymentRepository paymentRepository = new PaymentRepositoryInMemoryImpl();
    private final LoanServiceImpl loanServiceImpl;
    private final BalanceServiceImpl balanceServiceImpl;
    private final IPaymentService paymentService;

    public LedgerSystem(@NonNull ILoanRepository loanRepository, @NonNull IBorrowerRepository borrowerRepository, @NonNull IPaymentRepository paymentRepository) {
        loanServiceImpl = new LoanServiceImpl(loanRepository, borrowerRepository);
        balanceServiceImpl = new BalanceServiceImpl(loanRepository, borrowerRepository);
        paymentService = new PaymentServiceImpl(paymentRepository, loanRepository, borrowerRepository);
    }

    public static void main(String[] args) throws FileNotFoundException {
        LedgerSystem ledgerSystem = new LedgerSystem(loanRepository, borrowerRepository, paymentRepository);
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String command = sc.nextLine();
            parseCommandAndExecute(command, ledgerSystem);
        }
    }

    public static void parseCommandAndExecute(String command, LedgerSystem ledgerSystem){
        String[] data = command.split(" ");
        switch (data[0]){
            case "LOAN":
                ledgerSystem.loanServiceImpl.createLoan(data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                break;

            case "PAYMENT":
                ledgerSystem.paymentService.recordPayment(data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                break;

            case "BALANCE":
                ledgerSystem.balanceServiceImpl.getBalance(data[1], data[2], Integer.parseInt(data[3]));
                break;

            default:
                System.out.println("This " + command + " is not valid");
        }
    }
}
