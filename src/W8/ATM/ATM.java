package W8.ATM;

import java.util.Scanner;

public class ATM {
    static DBconnection dbConnection;
    static Account currentAccount;
    static Scanner scanner;

    public static void main(String[] args) {
        dbConnection = new DBconnection("jdbc:mysql://localhost:3306/myatm", "root", "1234");
        if (!dbConnection.connect()) {
            System.out.println("Failed to connect to the database.");
            return;
        }

        scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("\n===== ATM System =====");
            if (currentAccount == null) {
                System.out.println("1. Register");
                System.out.println("2. Log in");
                System.out.println("0. Exit");
            } else {
                System.out.println("1. Check balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Log out");
                System.out.println("0. Exit");
            }
            System.out.print("Enter your choice: ");
            choice = validateIntegerInput();
            switch (choice) {
                case 1 -> {
                    if (currentAccount == null) {
                        register();
                    } else {
                        currentAccount.checkBalance();
                        System.out.println("Account Type: "
                                + (currentAccount.getAccountType().equals("Credit")
                                        ? "Credit (Negative balance allowed)"
                                        : "Debit (Negative balance not allowed)"));
                    }
                }
                case 2 -> {
                    if (currentAccount == null) {
                        logIn();
                    } else {
                        withdraw();
                    }
                }
                case 3 -> {
                    if (currentAccount != null) {
                        deposit();
                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                }
                case 4 -> {
                    if (currentAccount != null) {
                        currentAccount.logOut();
                        currentAccount = null;
                    } else {
                        System.out.println("Invalid choice, please try again.");
                    }
                }

                case 0 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    dbConnection.disconnect();
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = validateDoubleInput();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }
        if (currentAccount.deposit(amount)) {
            System.out.println("Deposit successful.");
            System.out.println("New Balance: £" + currentAccount.getBalance());
        } else {
            System.out.println("Deposit failed. Please try again.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = validateDoubleInput();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }
        if (currentAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
            System.out.println("New Balance: "
                    + (currentAccount.getAccountType().equals("Credit") ? "Can go negative" : "Must remain positive"));
        } else {
            System.out.println("Insufficient balance or withdrawal failed.");
        }
    }

    private static void logIn() {
        System.out.print("Enter your id: ");
        int id = validateIntegerInput();
        scanner.nextLine();
        // System.out.println("Enter your password: ");
        // String password = scanner.nextLine();
        String password = validateNonEmptyString("password");
        ;
        System.out.println(id);
        System.out.println(password);
        currentAccount = new Account(dbConnection, id, password);
        if (currentAccount.logIn()) {
            System.out.println("Logged in successfully");
            System.out.println("Account Type: "
                    + (currentAccount.getAccountType().equals("Credit") ? "Credit (Negative balance allowed)"
                            : "Debit (Negative balance not allowed)"));
        } else {
            System.out.println("Login failed. Invalid ID or password.");
            currentAccount = null;
        }
    }

    public static void register() {
        System.out.print("Account type (1 - Debit, 2 - Credit): ");
        int accountType = validateIntegerInput();
        if (accountType != 1 && accountType != 2) {
            System.out.println("Invalid account type. Please select 1 or 2.");
            return;
        }
        scanner.nextLine();

        String forename = validateNonEmptyString("forename");
        String surname = validateNonEmptyString("surname");
        String postcode = validateNonEmptyString("postcode");
        System.out.print("Enter your password (max 8 characters): ");
        String password;
        while (true) {
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please enter a valid password:");
            } else if (password.length() > 8) {
                System.out.println("Password too long. Please use 8 characters or fewer:");
            } else {
                break;
            }
        }

        currentAccount = new Account(dbConnection);
        if (currentAccount.register(accountType, forename, surname, postcode, password)) {
            System.out.println("Registration successful. You are now logged in.");
        } else {
            System.out.println("Registration failed. Please try again.");
            currentAccount = null;
        }
    }

    private static int validateIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double validateDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static String validateNonEmptyString(String fieldName) {
        String input;
        while (true) {
            System.out.print("Enter your " + fieldName + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(fieldName + " cannot be empty. Please enter a valid " + fieldName + ".");
            } else {
                break;
            }
        }
        return input;
    }
}
