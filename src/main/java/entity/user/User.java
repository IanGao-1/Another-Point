package entity.user;

import entity.account.Account;
import entity.bank.Bank;
import method.IReportable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements IReportable {
    private final long userId;
    private final String name;
    private final String email;
    private final List<Account> accounts;

    public User(String name, String email) {
        this.userId = Bank.getNextUserId();
        this.name = name;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.printf("Account %d added to user %s (ID: %d)%n",
                account.getAccountNumber(), name, userId);
    }

    public boolean removeAccount(long accountNumber) {
        Account toRemove = getAccount(accountNumber);
        if (toRemove != null) {
            accounts.remove(toRemove);
            System.out.printf("Account %d removed from user %s (ID: %d)%n",
                    accountNumber, name, userId);
            return true;
        }
        System.out.printf("Account %d not found for user %s (ID: %d)%n",
                accountNumber, name, userId);
        return false;
    }

    public Account getAccount(long accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(null);
    }

    // Getters
    public long getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Account> getAccounts() { return Collections.unmodifiableList(accounts); }

    @Override
    public void displayReport() {
        System.out.println("\n=== User Report ===");
        System.out.printf("User ID: %d | Name: %s | Email: %s%n", userId, name, email);
        System.out.println("Accounts:");
        if (accounts.isEmpty()) {
            System.out.println("  No accounts found.");
        } else {
            accounts.forEach(a -> { System.out.print("  - "); a.displayReport(); });
        }
    }
}