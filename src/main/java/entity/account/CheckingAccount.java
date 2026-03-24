package src.main.java.entity.account;//package entity.account;

import src.main.java.entity.user.User;

public class CheckingAccount extends Account {
    private final double overdraftLimit;

    public CheckingAccount(User owner, double initialDeposit, double overdraftLimit) {
        super(owner, "Checking", initialDeposit);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() { return overdraftLimit; }

    @Override
    public boolean withdraw(double amount) {
        double availableFunds = getBalance() + overdraftLimit;
        if (amount > availableFunds) {
            System.out.printf("Withdrawal failed: Insufficient funds (including overdraft) in account %d. " +
                    "Requested: $%.2f, Available: $%.2f%n", getAccountNumber(), amount, availableFunds);
            return false;
        }
        this.balance -= amount;
        System.out.printf("Withdrew $%.2f from account %d. New balance: $%.2f%n",
                amount, getAccountNumber(), getBalance());
        return true;
    }

    @Override
    public void displayReport() {
        System.out.printf("Account Number: %d | Type: Checking | Balance: $%.2f | Overdraft Limit: $%.2f | Owner: %s (ID: %d)%n",
                getAccountNumber(), getBalance(), overdraftLimit, getOwner().getName(), getOwner().getUserId());
    }
}