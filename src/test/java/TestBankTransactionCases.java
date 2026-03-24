package src.test.java;

import src.main.java.entity.account.CDAccount;
import src.main.java.entity.account.CheckingAccount;
import src.main.java.entity.account.SavingsAccount;
import src.main.java.entity.bank.Bank;
import src.main.java.entity.user.User;

import java.time.LocalDate;

public class TestBankTransactionCases {
    public static void main(String[] args) {
        run("Global Trust Bank - Xi'an Another Point Branch");
    }

    public static void run(String bankName) {
        Bank bank = new Bank(bankName);

        User alice = bank.addUser("Alice Smith", "alice@example.com");
        User bob = bank.addUser("Bob Johnson", "bob@example.com");
        User charlie = bank.addUser("Charlie Brown", "charlie@example.com");

        SavingsAccount aliceSavings = bank.openSavingsAccount(alice.getUserId(), 5000.0, 0.025);
        CheckingAccount aliceChecking = bank.openCheckingAccount(alice.getUserId(), 2000.0, 500.0);
        CDAccount aliceCD = bank.openCDAccount(alice.getUserId(), 10000.0, 12);
        SavingsAccount bobSavings = bank.openSavingsAccount(bob.getUserId(), 3000.0, 0.02);
        CDAccount bobMatureCD = bank.openCDAccount(bob.getUserId(), 8000.0, 6, LocalDate.now().minusMonths(1));
        CheckingAccount charlieChecking = bank.openCheckingAccount(charlie.getUserId(), 1000.0, 200.0);

        System.out.println("\n--- Deposit Test Cases ---");
        bank.depositToAccount(alice.getUserId(), aliceSavings.getAccountNumber(), 1000.0);
        bank.depositToAccount(alice.getUserId(), aliceSavings.getAccountNumber(), -500.0);

        System.out.println("\n--- Withdrawal Test Cases ---");
        bank.withdrawFromAccount(alice.getUserId(), aliceChecking.getAccountNumber(), 1500.0);
        bank.withdrawFromAccount(alice.getUserId(), aliceChecking.getAccountNumber(), 800.0);
        bank.withdrawFromAccount(alice.getUserId(), aliceCD.getAccountNumber(), 2000.0);
        bank.withdrawFromAccount(bob.getUserId(), bobMatureCD.getAccountNumber(), 3000.0);
        bank.withdrawFromAccount(bob.getUserId(), bobSavings.getAccountNumber(), 5000.0);

        System.out.println("\n--- Close Account Test Case ---");
        bank.closeAccount(charlie.getUserId(), charlieChecking.getAccountNumber());
        charlie.displayReport();
    }
}
