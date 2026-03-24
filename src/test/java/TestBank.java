package src.test.java;

import src.main.java.entity.account.CDAccount;
import src.main.java.entity.account.CheckingAccount;
import src.main.java.entity.account.SavingsAccount;
import src.main.java.entity.bank.Bank;
import src.main.java.entity.user.User;

import java.time.LocalDate;

public class TestBank {
    public static void main(String[] args) {
        System.out.println("=== Starting Online Banking System Demo ===");

        // Create bank
        Bank bank = new Bank("Global Trust Bank");

        // Create users (3 total)
        System.out.println("\n--- Creating Users ---");

        User alice = bank.addUser("Alice Smith", "alice@example.com");

        // Test Duplicate User
//        User alice1 = bank.addUser("Alice Smith", "alice@example.com");

        User bob = bank.addUser("Bob Johnson", "bob@example.com");
        User charlie = bank.addUser("Charlie Brown", "charlie@example.com");

        // Create accounts (max 3 per user)
        System.out.println("\n--- Creating Accounts ---");
        SavingsAccount aliceSavings = bank.openSavingsAccount(alice.getUserId(), 5000.0, 0.025);
        CheckingAccount aliceChecking = bank.openCheckingAccount(alice.getUserId(), 2000.0, 500.0);
        CDAccount aliceCD = bank.openCDAccount(alice.getUserId(), 10000.0, 12);

        SavingsAccount bobSavings = bank.openSavingsAccount(bob.getUserId(), 3000.0, 0.02);
        CDAccount bobMatureCD = bank.openCDAccount(bob.getUserId(), 8000.0, 6, LocalDate.now().minusMonths(1));

        CheckingAccount charlieChecking = bank.openCheckingAccount(charlie.getUserId(), 1000.0, 200.0);

        // Demonstrate transactions
        System.out.println("\n--- Demonstrating Transactions ---");
        bank.depositToAccount(alice.getUserId(), aliceSavings.getAccountNumber(), 1000.0);
        bank.depositToAccount(alice.getUserId(), aliceSavings.getAccountNumber(), -500.0); // Should fail
        bank.withdrawFromAccount(alice.getUserId(), aliceChecking.getAccountNumber(), 1500.0);
        bank.withdrawFromAccount(alice.getUserId(), aliceChecking.getAccountNumber(), 800.0); // Overdraft
        bank.withdrawFromAccount(alice.getUserId(), aliceCD.getAccountNumber(), 2000.0); // Immature CD
        bank.withdrawFromAccount(bob.getUserId(), bobMatureCD.getAccountNumber(), 3000.0); // Mature CD
        bank.withdrawFromAccount(bob.getUserId(), bobSavings.getAccountNumber(), 5000.0); // Insufficient funds

        // Close account
        System.out.println("\n--- Closing Account ---");
        bank.closeAccount(charlie.getUserId(), charlieChecking.getAccountNumber());

        // Display reports
        System.out.println("\n--- Generating Reports ---");
        alice.displayReport();
        bob.displayReport();
        charlie.displayReport();
        bank.displayReport();

        System.out.println("\n=== Demo Complete ===");

        // Demonstrate transfer
        System.out.println("\n--- Demonstrating Transfer ---");

        // ✅ Normal transfer
        System.out.println("\nTransfer 1: Alice Savings -> Bob Savings (500)");
        aliceSavings.transfer(bobSavings, 500.0);
        aliceSavings.displayReport();
        bobSavings.displayReport();

        // ❌ Insufficient funds transfer
        System.out.println("\nTransfer 2: Alice Checking -> Bob Savings (5000)");
        aliceChecking.transfer(bobSavings, 5000.0);

        // ❌ Transfer to self
        System.out.println("\nTransfer 3: Alice Savings -> Alice Savings (100)");
        aliceSavings.transfer(aliceSavings, 100.0);

    }
}