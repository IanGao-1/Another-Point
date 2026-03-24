package src.test.java;

public class TestBank {
    public static void main(String[] args) {
        System.out.println("=== Starting Online Banking System Demo ===");

        System.out.println("\n[1] User and account test cases");
        TestBankUserAccountCases.main(args);

        System.out.println("\n[2] Transaction and account closing test cases");
        TestBankTransactionCases.main(args);

        System.out.println("\n[3] Transfer test cases");
        TestBankTransferCases.main(args);

        System.out.println("\n=== Demo Complete ===");
    }
}
