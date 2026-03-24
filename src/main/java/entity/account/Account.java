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
    /**
     * 转账到另一个账户
     */
    public boolean transfer(Account targetAccount, double amount) {
        // 1. 验证目标账户
        if (targetAccount == null) {
            System.out.println("Error: Target account cannot be null");
            return false;
        }

        // 2. 防止转账给自己
        if (this.accountNumber == targetAccount.getAccountNumber()) {
            System.out.println("Error: Cannot transfer to the same account");
            return false;
        }

        // 3. 先取款
        if (!this.withdraw(amount)) {
            return false;
        }

        // 4. 再存款到目标账户
        try {
            targetAccount.deposit(amount);
            System.out.printf("Transferred $%.2f from account %d to account %d%n",
                    amount, this.accountNumber, targetAccount.getAccountNumber());
            return true;
        } catch (IllegalArgumentException e) {
            // 如果目标账户存款失败，需要回滚取款
            this.deposit(amount);
            System.out.printf("Transfer failed: %s%n", e.getMessage());
            return false;
        }
    }
}