package bookStoreDB.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Tempzp22062002.";
    public DatabaseConnection() {
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
