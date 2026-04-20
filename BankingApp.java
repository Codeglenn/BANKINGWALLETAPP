import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String name;
    private double balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawal successful.");
    }
}

public class BankingApp {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n******************************");
            System.out.println("   DEMO WALLET SYSTEM");
            System.out.println("******************************");
            System.out.println("1: Create Account");
            System.out.println("2: Select Account");
            System.out.println("3: Exit");
            System.out.println("******************************");

            int choice = getIntInput("Choose option: ");

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> selectAccount();
                case 3 -> isRunning = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    static void createAccount() {
        System.out.print("Enter account name: ");
        String name = scanner.next();

        if (accounts.containsKey(name)) {
            System.out.println("Account already exists.");
            return;
        }

        accounts.put(name, new Account(name));
        System.out.println("Account created successfully.");
    }

    static void selectAccount() {
        System.out.print("Enter account name: ");
        String name = scanner.next();

        Account account = accounts.get(name);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        accountMenu(account);
    }

    static void accountMenu(Account account) {
        boolean inAccount = true;

        while (inAccount) {
            System.out.println("\n--- Account: " + account.getName() + " ---");
            System.out.println("1: Show Balance");
            System.out.println("2: Deposit");
            System.out.println("3: Withdraw");
            System.out.println("4: Back");

            int choice = getIntInput("Choose option: ");

            switch (choice) {
                case 1 -> System.out.printf("Balance: Ksh %.2f\n", account.getBalance());
                case 2 -> {
                    double amount = getDoubleInput("Enter deposit amount: ");
                    account.deposit(amount);
                }
                case 3 -> {
                    double amount = getDoubleInput("Enter withdrawal amount: ");
                    account.withdraw(amount);
                }
                case 4 -> inAccount = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // Safe integer input
    static int getIntInput(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Enter a number.");
                scanner.next();
            }
        }
    }

    // Safe double input
    static double getDoubleInput(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Enter a valid amount.");
                scanner.next();
            }
        }
    }
}