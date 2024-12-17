package bookStoreDB.Transactions.WarehouseSupplierTransaction;

import bookStoreDB.utils.DatabaseConnection;
import bookStoreDB.utils.SQLExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseSupplierTransactionDAO {

    public void addTransaction(WarehouseSupplierTransaction transaction) {
        String sql = "INSERT INTO warehouse_supplier_transactions (book_id, supplier_id, quantity, supply_price, supply_date, note) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(transaction, statement);

            statement.executeUpdate();
            System.out.println("Transaction added successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void updateTransaction(int id, WarehouseSupplierTransaction transaction) {
        String sql = "UPDATE warehouse_supplier_transactions SET book_id = ?, supplier_id = ?, quantity = ?, supply_price = ?, supply_date = ?, note = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(transaction, statement);
            statement.setInt(7, id);

            statement.executeUpdate();
            System.out.println("Transaction updated successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    private void fillStatementParameters(WarehouseSupplierTransaction transaction, PreparedStatement statement) throws SQLException {
        statement.setInt(1, transaction.getBookId());
        statement.setInt(2, transaction.getSupplierId());
        statement.setInt(3, transaction.getQuantity());
        statement.setBigDecimal(4, transaction.getSupplyPrice());
        statement.setTimestamp(5, transaction.getSupplyDate());
        statement.setString(6, transaction.getNote());
    }

    public WarehouseSupplierTransaction getTransactionById(int id) {
        String sql = "SELECT * FROM warehouse_supplier_transactions WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new WarehouseSupplierTransaction(
                            rs.getInt("book_id"),
                            rs.getInt("supplier_id"),
                            rs.getInt("quantity"),
                            rs.getBigDecimal("supply_price"),
                            rs.getTimestamp("supply_date"),
                            rs.getString("note")
                    );
                } else {
                    System.out.println("Transaction not found");
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return null;
    }

    public List<WarehouseSupplierTransaction> getAllTransactions() {
        List<WarehouseSupplierTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM warehouse_supplier_transactions";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                WarehouseSupplierTransaction transaction = new WarehouseSupplierTransaction(
                        rs.getInt("book_id"),
                        rs.getInt("supplier_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("supply_price"),
                        rs.getTimestamp("supply_date"),
                        rs.getString("note")
                );
                transactions.add(transaction);
            }
            System.out.println(transactions.size() + " transactions retrieved.");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return transactions;
    }


    public void deleteTransaction(int id) {
        String sql = "DELETE FROM warehouse_supplier_transactions WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Transaction deleted successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }
}
