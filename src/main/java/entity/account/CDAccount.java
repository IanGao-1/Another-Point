package entity.account;

import entity.user.User;

import java.time.LocalDate;

public class CDAccount extends Account {
    private final LocalDate maturityDate;
    private final int termMonths;

    // Standard constructor (uses current date + term)
    public CDAccount(User owner, double initialDeposit, int termMonths) {
        this(owner, initialDeposit, termMonths, LocalDate.now().plusMonths(termMonths));
    }

    // Test constructor (explicit maturity date)
    public CDAccount(User owner, double initialDeposit, int termMonths, LocalDate maturityDate) {
        super(owner, "CD", initialDeposit);
        this.termMonths = termMonths;
        this.maturityDate = maturityDate;
    }

    public LocalDate getMaturityDate() { return maturityDate; }
    public int getTermMonths() { return termMonths; }

    @Override
    public boolean withdraw(double amount) {
        if (LocalDate.now().isBefore(maturityDate)) {
            System.out.printf("Withdrawal failed: CD account %d has not matured yet. " +
                    "Maturity date: %s%n", getAccountNumber(), maturityDate);
            return false;
        }
        return super.withdraw(amount);
    }

    @Override
    public void displayReport() {
        System.out.printf("Account Number: %d | Type: CD | Balance: $%.2f | Term: %d months | Maturity Date: %s | Owner: %s (ID: %d)%n",
                getAccountNumber(), getBalance(), termMonths, maturityDate, getOwner().getName(), getOwner().getUserId());
    }
}