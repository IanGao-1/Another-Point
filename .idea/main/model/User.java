

/**
 * 用户类 - 代表银行系统的用户
 * 每个用户可以拥有多个银行账户
 */
public class User {

    // ==================== 静态字段 ====================
    private static int nextUserId = 1000;      // 下一个可用的用户ID
    private static int totalUsers = 0;          // 系统总用户数

    // ==================== 实例字段 ====================
    private int userId;                         // 用户ID（唯一标识）
    private String username;                    // 真实姓名
    private String email;                       // 电子邮箱
    private String phoneNumber;                 // 手机号码
    private String idNumber;                    // 身份证号码
    private Date registrationDate;              // 注册日期
    private boolean isActive;                   // 账户状态（是否激活）
    private List<BankAccount> accounts;         // 该用户拥有的所有账户



    /**
     * 无参构造方法（用于框架或序列化）
     */
    public User() {
        this.accounts = new ArrayList<>();
        this.isActive = true;
        this.registrationDate = new Date();
    }

    /**
     * 带必要信息的构造方法
     * @param username 用户名
     * @param password 密码
     * @param fullName 真实姓名
     * @param email 邮箱
     */
    public User(String username, String email, String phoneNumber, String idNumber) {
        this(); // 调用无参构造，初始化默认值

        this.userId =  nextUserId++; // 自动生成用户ID
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
        // 更新统计
        totalUsers++;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "user{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", isActive=" + isActive +
                ", accounts=" + accounts +
                '}';
    }
}