package src.test.java;

public class TestBank {
    private static final String BANK_NAME = "Global Trust Bank - Xi'an Another Point Branch";

    public static void main(String[] args) {
        System.out.println("=== Starting Online Banking System Demo ===");

        System.out.println("\n[1] User and account test cases");
        TestBankUserAccountCases.run(BANK_NAME);

        System.out.println("\n[2] Transaction and account closing test cases");
        TestBankTransactionCases.run(BANK_NAME);

        System.out.println("\n[3] Transfer test cases");
        TestBankTransferCases.run(BANK_NAME);

        System.out.println("\n=== Demo Complete ===");
    }
}
