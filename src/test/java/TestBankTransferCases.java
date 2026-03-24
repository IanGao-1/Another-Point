package src.test.java;

import src.main.java.entity.account.CheckingAccount;
import src.main.java.entity.account.SavingsAccount;
import src.main.java.entity.bank.Bank;
import src.main.java.entity.user.User;

public class TestBankTransferCases {
    public static void main(String[] args) {
        run("Global Trust Bank - Xi'an Another Point Branch");
    }

    public static void run(String bankName) {
        Bank bank = new Bank(bankName);

        User alice = bank.addUser("Alice Smith", "alice@example.com");
        User bob = bank.addUser("Bob Johnson", "bob@example.com");

        SavingsAccount aliceSavings = bank.openSavingsAccount(alice.getUserId(), 5000.0, 0.025);
        CheckingAccount aliceChecking = bank.openCheckingAccount(alice.getUserId(), 2000.0, 500.0);
        SavingsAccount bobSavings = bank.openSavingsAccount(bob.getUserId(), 3000.0, 0.02);

        System.out.println("\n--- Transfer Test Cases ---");

        System.out.println("\nTransfer 1: Alice Savings -> Bob Savings (500)");
        aliceSavings.transfer(bobSavings, 500.0);
        aliceSavings.displayReport();
        bobSavings.displayReport();

        System.out.println("\nTransfer 2: Alice Checking -> Bob Savings (5000)");
        aliceChecking.transfer(bobSavings, 5000.0);

        System.out.println("\nTransfer 3: Alice Savings -> Alice Savings (100)");
        aliceSavings.transfer(aliceSavings, 100.0);
    }
}
