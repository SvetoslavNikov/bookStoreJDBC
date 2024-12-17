package bookStoreDB.suppliers;

import bookStoreDB.utils.DatabaseConnection;
import bookStoreDB.utils.SQLExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    /*
        Methods:

        1. public void addSupplier(Supplier supplier)
            - Adds a new supplier to the database using an INSERT statement

        2. public void updateSupplier(int id, Supplier supplier)
            - Updates an existing supplier in the database based on the provided ID

        3. public Supplier getSupplierById(int id)
            - Retrieves a supplier from the database based on the provided ID

        4. public List<Supplier> getAllSuppliers()
            - Retrieves a list of all suppliers from the database

        5. public void supplierTablePrint()
            - Prints all suppliers from the database to the console

        6. public void deleteSupplier(int id)
            - Deletes a supplier from the database based on the provided ID
    */

    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO suppliers (name, phone_number, email, address, note)"
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(supplier, statement);
            statement.executeUpdate();
            System.out.println("The supplier was added successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void updateSupplier(int id, Supplier supplier) {
        String sql = "UPDATE suppliers SET name = ?, phone_number = ?, email = ?, address = ?, note = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(supplier, statement);
            statement.setInt(6, id);
            statement.executeUpdate();
            System.out.println("The supplier was updated successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    private void fillStatementParameters(Supplier supplier, PreparedStatement statement) throws SQLException {
        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getPhoneNumber());
        statement.setString(3, supplier.getEmail());
        statement.setString(4, supplier.getAddress());
        statement.setString(5, supplier.getNote());
    }

    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM suppliers WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("note")
                    );
                } else {
                    System.out.println("Supplier not found");
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            int counter = 0;
            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note")
                );
                suppliers.add(supplier);
                counter++;
            }
            System.out.println(counter + " suppliers added to the list successfully.");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return suppliers;
    }

    public void supplierTablePrint() {
        String sql = "SELECT * FROM suppliers";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.printf("%-5s %-20s %-15s %-30s %-20s %s\n",
                    "ID", "|Name", "|Phone Number", "|Email", "|Address", "|Note");
            System.out.println("------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(" %-5s %-20s %-15s %-30s %-40s %s\n",
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("note"));
            }

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void deleteSupplier(int id) {
        String sql = "DELETE FROM suppliers WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public List<Supplier> searchSuppliersByName(String nameSearch) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, "%" + nameSearch + "%");

            try (ResultSet rs = statement.executeQuery()) {
                int counter = 0;
                while (rs.next()) {
                    Supplier supplier = new Supplier(
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("note")
                    );
                    suppliers.add(supplier);
                    counter++;
                }
                System.out.println(counter + " suppliers found with the name '" + nameSearch + "'.");

            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return suppliers;
    }
}
