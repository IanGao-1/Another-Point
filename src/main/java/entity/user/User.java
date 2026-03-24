package entity.user;

import entity.account.Account;
import entity.bank.Bank;
import method.IReportable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User implements IReportable {
    // ==================== 静态字段 ====================
    private static int nextUserId = 1000;      // 下一个可用的用户ID
    private static int totalUsers = 0;          // 系统总用户数

    // ==================== 实例字段 ====================
    private int userId;                         // 用户ID（唯一标识）
    private String userName;                    // 真实姓名
    private String email;                       // 电子邮箱
    private String phoneNumber;                 // 手机号码
    private String idNumber;                    // 身份证号码
    private Date registrationDate;              // 注册日期
    private boolean isActive;                   // 账户状态（是否激活）
    private List<Account> accounts;         // 该用户拥有的所有账户

    // ==================== 构造方法 ====================

    /**
     * 无参构造方法（用于框架或序列化）
     */
    public User() {
        this.accounts = new ArrayList<>();
        this.isActive = true;
        this.registrationDate = new Date();
    }

    public User(String userName, String email, String phoneNumber, String idNumber) {
        this(); // 调用无参构造，初始化默认值

        this.userId = nextUserId++;  // 自动生成用户ID
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        this.email = email;

        // 更新统计
        totalUsers++;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account getAccount(long accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;  // 未找到
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * 添加用户的账户
     * @param account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * 根据 accountNumber 删除用户对应的账户
     * @param accountNumber
     * @return
     */
    public boolean removeAccount(long accountNumber) {
        // 查找并删除
        return accounts.removeIf(account -> account.getAccountNumber() == accountNumber);
    }


    @Override
    public void displayReport() {
        System.out.println("User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", isActive=" + isActive +
                ", accounts=" + accounts +
                '}');
    }


}