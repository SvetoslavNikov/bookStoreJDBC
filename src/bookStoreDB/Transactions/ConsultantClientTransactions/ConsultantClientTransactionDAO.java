package bookStoreDB.Transactions.ConsultantClientTransactions;

import bookStoreDB.utils.DatabaseConnection;
import bookStoreDB.utils.SQLExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultantClientTransactionDAO {

    // Add a new transaction to the database
    public void addTransaction(ConsultantClientTransaction transaction) {
        String sql = "INSERT INTO consultant_client_transactions (book_id, consultant_id, client_id, sale_date, quantity, total_price, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            fillStatementParameters(transaction, statement);

            statement.executeUpdate();
            System.out.println("Transaction added successfully!");

            // Retrieve generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    // Update an existing transaction by ID
    public void updateTransaction(int id, ConsultantClientTransaction transaction) {
        String sql = "UPDATE consultant_client_transactions SET book_id = ?, consultant_id = ?, client_id = ?, sale_date = ?, quantity = ?, total_price = ?, notes = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(transaction, statement);
            statement.setInt(8, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Transaction updated successfully!");
            } else {
                System.out.println("No transaction found with ID " + id);
            }

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    private void fillStatementParameters(ConsultantClientTransaction transaction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, transaction.getBookId());
        statement.setInt(2, transaction.getConsultantId());
        statement.setInt(3, transaction.getClientId());
        statement.setTimestamp(4, transaction.getSaleDate());
        statement.setInt(5, transaction.getQuantity());
        statement.setBigDecimal(6, transaction.getTotalPrice());
        statement.setString(7, transaction.getNotes());
    }

    // Retrieve a transaction by its ID
    public ConsultantClientTransaction getTransactionById(int id) {
        String sql = "SELECT * FROM consultant_client_transactions WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new ConsultantClientTransaction(
                            rs.getInt("book_id"),
                            rs.getInt("consultant_id"),
                            rs.getInt("client_id"),
                            rs.getTimestamp("sale_date"),
                            rs.getInt("quantity"),
                            rs.getBigDecimal("total_price"),
                            rs.getString("notes")
                    );
                } else {
                    System.out.println("Transaction not found.");
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return null;
    }

    // Retrieve all transactions
    public List<ConsultantClientTransaction> getAllTransactions() {
        List<ConsultantClientTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM consultant_client_transactions";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                ConsultantClientTransaction transaction = new ConsultantClientTransaction(
                        rs.getInt("book_id"),
                        rs.getInt("consultant_id"),
                        rs.getInt("client_id"),
                        rs.getTimestamp("sale_date"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("notes")
                );
                transaction.setId(rs.getInt("id"));
                transactions.add(transaction);
            }
            System.out.println(transactions.size() + " transactions retrieved.");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return transactions;
    }



    // Delete a transaction by ID
    public void deleteTransaction(int id) {
        String sql = "DELETE FROM consultant_client_transactions WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Transaction deleted successfully!");
            } else {
                System.out.println("No transaction found with ID " + id);
            }

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    // Search transactions by client ID
    public List<ConsultantClientTransaction> searchTransactionsByClientId(int clientId) {
        List<ConsultantClientTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM consultant_client_transactions WHERE client_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clientId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    ConsultantClientTransaction transaction = new ConsultantClientTransaction(
                            rs.getInt("book_id"),
                            rs.getInt("consultant_id"),
                            rs.getInt("client_id"),
                            rs.getTimestamp("sale_date"),
                            rs.getInt("quantity"),
                            rs.getBigDecimal("total_price"),
                            rs.getString("notes")
                    );
                    transaction.setId(rs.getInt("id"));
                    transactions.add(transaction);
                }
                System.out.println(transactions.size() + " transactions found for client ID " + clientId);
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return transactions;
    }
}
