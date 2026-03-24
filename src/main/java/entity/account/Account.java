package entity.account;

import entity.bank.Bank;
import entity.user.User;
import method.IReportable;

public abstract class Account implements IReportable {
    private final long accountNumber;
    protected double balance;
    private final User owner;
    private final String accountType;
    private AccountStatus accountStatus;

    // 冻结/解冻方法
    public void freezeAccount() {
        this.accountStatus = AccountStatus.FROZEN;
        System.out.println("账户已冻结：" + getAccountNumber());
    }
    public void unfreezeAccount() {
        this.accountStatus = AccountStatus.NORMAL;
        System.out.println("账户已解冻：" + getAccountNumber());
    }
    // 状态校验
    public boolean isFrozen() {
        return accountStatus == AccountStatus.FROZEN;
    }

    public Account(User owner, String accountType, double initialDeposit) {
        this.accountNumber = Bank.getNextAccountNumber();
        this.owner = owner;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.accountStatus = AccountStatus.NORMAL;
    }

    public void deposit(double amount) {
        if (accountStatus == AccountStatus.FROZEN) {
            System.out.println("Deposit failed: Account has been frozen!");
            throw new IllegalArgumentException("Account has been frozen!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance += amount;
        System.out.printf("Deposited $%.2f to account %d. New balance: $%.2f%n",
                amount, accountNumber, balance);
    }

    public boolean withdraw(double amount) {
        if (accountStatus == AccountStatus.FROZEN) {
            System.out.println("Deposit failed: Account has been frozen!");
            return false;
        }
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
