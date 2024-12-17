package bookStoreDB.utils;
import java.sql.SQLException;

public class SQLExceptionHandler {
    public static void handleSQLException(SQLException e) {
        System.err.println("SQLState: " + e.getSQLState() + '\n');
        System.err.println("Error Code: " + e.getErrorCode() + '\n');
        System.err.println("Message: " + e.getMessage() + '\n');
        e.printStackTrace();
    }
}
