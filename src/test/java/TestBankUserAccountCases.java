package src.test.java;

import src.main.java.entity.account.CDAccount;
import src.main.java.entity.account.CheckingAccount;
import src.main.java.entity.account.SavingsAccount;
import src.main.java.entity.bank.Bank;
import src.main.java.entity.user.User;

import java.time.LocalDate;

public class TestBankUserAccountCases {
    public static void main(String[] args) {
        System.out.println("\n--- Creating Users ---");

        Bank bank = new Bank("Global Trust Bank");

        User alice = bank.addUser("Alice Smith", "alice@example.com");
        bank.addUser("Alice Smith", "alice@example.com");
        User bob = bank.addUser("Bob Johnson", "bob@example.com");
        User charlie = bank.addUser("Charlie Brown", "charlie@example.com");

        System.out.println("\n--- Opening Accounts ---");
        SavingsAccount aliceSavings = bank.openSavingsAccount(alice.getUserId(), 5000.0, 0.025);
        CheckingAccount aliceChecking = bank.openCheckingAccount(alice.getUserId(), 2000.0, 500.0);
        CDAccount aliceCD = bank.openCDAccount(alice.getUserId(), 10000.0, 12);

        bank.openSavingsAccount(bob.getUserId(), 3000.0, 0.02);
        bank.openCDAccount(bob.getUserId(), 8000.0, 6, LocalDate.now().minusMonths(1));
        bank.openCheckingAccount(charlie.getUserId(), 1000.0, 200.0);

        System.out.println("\n--- User Report Preview ---");
        alice.displayReport();

        System.out.println("\n--- Account Report Preview ---");
        aliceSavings.displayReport();
        aliceChecking.displayReport();
        aliceCD.displayReport();

        System.out.println("\n--- Bank Report Preview ---");
        bank.displayReport();
    }
}
