package W8.ATM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Account {
    private int id = -1;
    private String password;
    private String accountType; // Debit or Credit
    private double balance;
    private Connection connection;

    public Account(DBconnection connection) {
        this.connection = connection.getConnection();
    }

    public Account(DBconnection connection, int id, String password) {
        this.connection = connection.getConnection();
        this.id = id;
        this.password = password;
    }

    public boolean logIn() {
        try {
            String query = "SELECT u.id, a.savetype, a.saving FROM users u " +
                    "JOIN accounts a ON u.id = a.userid WHERE u.id = ? AND u.password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
                accountType = resultSet.getString("savetype");
                balance = resultSet.getDouble("saving");
                resultSet.close();
                System.out.println("Account Type: " + accountType);
                return true;
            } else {
                resultSet.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return false;
    }

    public boolean register(int accountTypeInput, String forename, String surname, String postcode, String password) {
        try {
            String accountTypeStr = accountTypeInput == 2 ? "Credit" : "Debit";

            String insertUserQuery = "INSERT INTO users (forename, surname, postcode, password) VALUES (?, ?, ?, ?)";
            PreparedStatement userStatement = connection.prepareStatement(insertUserQuery,
                    Statement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, forename);
            userStatement.setString(2, surname);
            userStatement.setString(3, postcode);
            userStatement.setString(4, password);
            userStatement.executeUpdate();

            ResultSet generatedKeys = userStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            userStatement.close();

            String insertAccountQuery = "INSERT INTO accounts (savetype, saving, userid) VALUES (?, ?, ?)";
            PreparedStatement accountStatement = connection.prepareStatement(insertAccountQuery);
            accountStatement.setString(1, accountTypeStr);
            accountStatement.setDouble(2, 0.0);
            accountStatement.setInt(3, id);
            accountStatement.executeUpdate();
            accountStatement.close();

            this.accountType = accountTypeStr;
            this.balance = 0.0;
            return true;
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    public boolean logOut() {
        id = -1;
        password = "";
        return true;
    }

    public void checkBalance() {
        System.out.println("Your current balance is: £" + balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }

        if (accountType.equals("Debit") && balance < amount) {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        }

        try {
            String updateQuery = "UPDATE accounts SET saving = saving - ? WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            balance -= amount;
            System.out.println("Withdrawal successful. New balance: £" + balance);
            return true;
        } catch (Exception e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
            return false;
        }
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return false;
        }

        try {
            String updateQuery = "UPDATE accounts SET saving = saving + ? WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            balance += amount;
            System.out.println("Deposit successful. New balance: £" + balance);
            return true;
        } catch (Exception e) {
            System.out.println("Error during deposit: " + e.getMessage());
            return false;
        }
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
}
