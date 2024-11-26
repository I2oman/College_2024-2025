package W8.ATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private String url;
    private String username;
    private String password;
    private static Connection connection;

    public DBconnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully.");
            return true;
        } catch (SQLException exception) {
            System.out.println("Database connection failed: " + exception.getMessage());
        }
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database disconnected successfully.");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error disconnecting database: " + e.getMessage());
        }
        return false;
    }
}
