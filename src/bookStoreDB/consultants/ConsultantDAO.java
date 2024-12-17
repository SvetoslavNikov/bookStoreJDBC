package bookStoreDB.consultants;

import bookStoreDB.utils.DatabaseConnection;
import bookStoreDB.utils.SQLExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultantDAO {

    public void addConsultant(Consultant consultant) {
        String sql = "INSERT INTO consultants (name, phone_number, email, address, note) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, consultant.getName());
            statement.setString(2, consultant.getPhoneNumber());
            statement.setString(3, consultant.getEmail());
            statement.setString(4, consultant.getAddress());
            statement.setString(5, consultant.getNote());

            statement.executeUpdate();
            System.out.println("Consultant added successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public Consultant getConsultantById(int id) {
        String sql = "SELECT * FROM consultants WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Consultant(
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("note")
                    );
                } else {
                    System.out.println("Consultant not found");
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return null;
    }

    public List<Consultant> getAllConsultants() {
        List<Consultant> consultants = new ArrayList<>();
        String sql = "SELECT * FROM consultants";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            int counter = 0;
            while (rs.next()) {
                Consultant consultant = new Consultant(
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note")
                );
                consultants.add(consultant);
                counter++;
            }
            System.out.println(counter + " consultants retrieved.");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return consultants;
    }

    public void printConsultantsTable() {
        String sql = "SELECT * FROM consultants";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.printf("%-5s %-20s %-15s %-20s %-30s %-40s\n",
                    "ID", "|Name", "|Phone Number", "|Email", "|Address", "|Note");
            System.out.println("----------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(" %-5s %-20s %-15s %-20s %-30s %-40s\n",
                        resultSet.getInt("id"),
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

    public void updateConsultant(int id, Consultant consultant) {
        String sql = "UPDATE consultants SET name = ?, phone_number = ?, email = ?, address = ?, note = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, consultant.getName());
            statement.setString(2, consultant.getPhoneNumber());
            statement.setString(3, consultant.getEmail());
            statement.setString(4, consultant.getAddress());
            statement.setString(5, consultant.getNote());
            statement.setInt(6, id);

            statement.executeUpdate();
            System.out.println("Consultant updated successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void deleteConsultant(int id) {
        String sql = "DELETE FROM consultants WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Consultant deleted successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public List<Consultant> searchConsultantsByName(String nameSearch) {
        List<Consultant> consultants = new ArrayList<>();
        String sql = "SELECT * FROM consultants WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + nameSearch + "%");

            try (ResultSet rs = statement.executeQuery()) {
                int counter = 0;
                while (rs.next()) {
                    Consultant consultant = new Consultant(
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("note")
                    );
                    consultants.add(consultant);
                    counter++;
                }
                System.out.println(counter + " consultants found.");

            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return consultants;
    }
}
