package entity.account;

import entity.bank.Bank;
import entity.user.User;
import method.IReportable;

public abstract class Account implements IReportable {
    private final long accountNumber;
    protected double balance;
    private final User owner;
    private final String accountType;

    public Account(User owner, String accountType, double initialDeposit) {
        this.accountNumber = Bank.getNextAccountNumber();
        this.owner = owner;
        this.accountType = accountType;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
        System.out.printf("Deposited $%.2f to account %d. New balance: $%.2f%n",
                amount, accountNumber, balance);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.printf("Withdrawal failed: Insufficient balance in account %d. " +
                    "Requested: $%.2f, Available: $%.2f%n", accountNumber, amount, balance);
            return false;
        }
        this.balance -= amount;
        System.out.printf("Withdrew $%.2f from account %d. New balance: $%.2f%n",
                amount, accountNumber, balance);
        return true;
    }

    // Getters
    public long getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public User getOwner() { return owner; }
    public String getAccountType() { return accountType; }

    @Override
    public void displayReport() {
        System.out.printf("Account Number: %d | Type: %s | Balance: $%.2f | Owner: %s (ID: %d)%n",
                accountNumber, accountType, balance, owner.getName(), owner.getUserId());
    }
}