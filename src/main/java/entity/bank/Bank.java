package src.main.java.entity.bank;

import src.main.java.entity.account.Account;
import src.main.java.entity.account.CDAccount;
import src.main.java.entity.account.CheckingAccount;
import src.main.java.entity.account.SavingsAccount;
import src.main.java.entity.user.User;
import method.IReportable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank implements IReportable {
    private final String bankName;
    private final List<User> users;

    private static long nextUserId = 1000;
    private static long nextAccountNumber = 100000;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.users = new ArrayList<>();
    }

    // Static ID generators
    public static long getNextUserId() { return nextUserId++; }
    public static long getNextAccountNumber() { return nextAccountNumber++; }

//     User management
    public User addUser(String name, String email) {

        // Check for duplicate user (same name and email)
        for (User existingUser : users) {
            if (existingUser.getName().equals(name) &&
                    existingUser.getEmail().equals(email)) {
                System.out.printf("Duplicate user skipped: %s (%s)%n", name, email);
                return null;
            }
        }

        User user = new User(name, email);
        users.add(user);
        System.out.printf("User added: %s (ID: %d, Email: %s)%n", name, user.getUserId(), email);
        return user;
    }

    public void addUsers(ArrayList<User> newUsers) {
        for (User user : newUsers) {
            addUser(user.getName(), user.getEmail());
        }
    }

    public User getUser(long userId) {
        return users.stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    // Account opening
    public SavingsAccount openSavingsAccount(long userId, double initialDeposit, double interestRate) {
        User user = getUser(userId);
        if (user == null) return null;
        SavingsAccount account = new SavingsAccount(user, initialDeposit, interestRate);
        user.addAccount(account);
        return account;
    }

    public CheckingAccount openCheckingAccount(long userId, double initialDeposit, double overdraftLimit) {
        User user = getUser(userId);
        if (user == null) return null;
        CheckingAccount account = new CheckingAccount(user, initialDeposit, overdraftLimit);
        user.addAccount(account);
        return account;
    }

    public CDAccount openCDAccount(long userId, double initialDeposit, int termMonths) {
        User user = getUser(userId);
        if (user == null) return null;
        CDAccount account = new CDAccount(user, initialDeposit, termMonths);
        user.addAccount(account);
        return account;
    }

    // Test CD opening (explicit maturity date)
    public CDAccount openCDAccount(long userId, double initialDeposit, int termMonths, LocalDate maturityDate) {
        User user = getUser(userId);
        if (user == null) return null;
        CDAccount account = new CDAccount(user, initialDeposit, termMonths, maturityDate);
        user.addAccount(account);
        return account;
    }

    // Account closing
    public boolean closeAccount(long userId, long accountNumber) {
        User user = getUser(userId);
        return user != null && user.removeAccount(accountNumber);
    }

    // Transactions
    public boolean depositToAccount(long userId, long accountNumber, double amount) {
        User user = getUser(userId);
        if (user == null) return false;
        Account account = user.getAccount(accountNumber);
        if (account == null) return false;
        try {
            account.deposit(amount);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.printf("Deposit failed: %s%n", e.getMessage());
            return false;
        }
    }

    public boolean withdrawFromAccount(long userId, long accountNumber, double amount) {
        User user = getUser(userId);
        if (user == null) return false;
        Account account = user.getAccount(accountNumber);
        return account != null && account.withdraw(amount);
    }

    // Getters
    public String getBankName() { return bankName; }
    public List<User> getUsers() { return Collections.unmodifiableList(users); }

    @Override
    public void displayReport() {
        System.out.println("\n=====================================");
        System.out.printf("=== Bank Report: %s ===", bankName);
        System.out.println("\n=====================================");
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(IReportable::displayReport);
        }
        System.out.println("=====================================");
    }
}