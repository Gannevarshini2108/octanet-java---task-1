import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {
    // Simulated user account
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize account with a starting balance and PIN
    public ATMSimulation(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    // Check account balance
    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
        transactionHistory.add("Checked balance: $" + balance);
    }

    // Deposit cash into account
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return;
        }
        balance += amount;
        System.out.println("You have successfully deposited $" + amount);
        transactionHistory.add("Deposited: $" + amount);
    }

    // Withdraw cash from account
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Your balance is: $" + balance);
            return;
        }
        balance -= amount;
        System.out.println("You have successfully withdrawn $" + amount);
        transactionHistory.add("Withdrew: $" + amount);
    }

    // Change PIN
    public void changePin(int newPin) {
        this.pin = newPin;
        System.out.println("Your PIN has been successfully changed.");
        transactionHistory.add("PIN changed");
    }

    // View transaction history
    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Validate user PIN
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    // Main method to simulate the ATM operations
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize ATM with a starting balance and PIN
        ATMSimulation atm = new ATMSimulation(1000.00, 1234);

        System.out.println("Welcome to the ATM Machine!");
        System.out.print("Please enter your PIN: ");
        int inputPin = scanner.nextInt();

        // Validate PIN
        if (!atm.validatePin(inputPin)) {
            System.out.println("Invalid PIN. Exiting.");
            return;
        }

        int choice;
        do {
            // Display menu options
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter your new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(newPin);
                    break;
                case 5:
                    atm.viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
