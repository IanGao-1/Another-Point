package entity.account;

import entity.user.User;

public class SavingsAccount extends Account {
    private final double interestRate;

    public SavingsAccount(User owner, double initialDeposit, double interestRate) {
        super(owner, "Savings", initialDeposit);
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    @Override
    public void displayReport() {
        System.out.printf("Account Number: %d | Type: Savings | Balance: $%.2f | Interest Rate: %.2f%% | Owner: %s (ID: %d)%n",
                getAccountNumber(), getBalance(), interestRate * 100, getOwner().getName(), getOwner().getUserId());
    }
}