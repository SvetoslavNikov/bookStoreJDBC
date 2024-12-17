package bookStoreDB.clients;

import bookStoreDB.utils.DatabaseConnection;
import bookStoreDB.utils.SQLExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    /*
        Methods:

        1. private Connection getConnection() throws SQLException
            - Establishes a connection to the PostgresSQL database.

        2. public void addClient(Client client)
            - Adds a new client to the database using an INSERT statement.

        3. public Client getClientByID(int id)
            - Retrieves a client from the database based on the provided ID.

        4. public List<Client> getAllClients()
            - Retrieves a list of all clients from the database.

        5. public void ClientsTablePrint()
            - Prints all clients from the database to the console in a formatted table.

        6. public void updateClient(int id, Client client)
            - Updates an existing client in the database based on the provided ID.

        7. public void deleteClient(int id)
            - Deletes a client from the database based on the provided ID.

        8. public List<Client> searchClientByName(String nameSearch)
            - Searches for clients in the database by name (case-insensitive).
    */

    public void addClient(Client client) {
        String sql = "INSERT INTO clients (consultant_id, name, phone_number, email, address, note)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(client, statement);

            statement.executeUpdate();
            System.out.println("The client was added successfully");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void updateClient(int id, Client client) {
        String sql = "UPDATE clients SET consultant_id = ?, name = ?, phone_number = ?," +
                " email = ?, address = ?, note = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(client, statement);
            statement.setInt(7, id);

            statement.executeUpdate();
            System.out.println("The client was updated successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    private void fillStatementParameters(Client client, PreparedStatement statement) throws SQLException {
        statement.setInt(1, client.getConsultantId());
        statement.setString(2, client.getName());
        statement.setString(3, client.getPhoneNumber());
        statement.setString(4, client.getEmail());
        statement.setString(5, client.getAddress());
        statement.setString(6, client.getNote());
    }

    public Client getClientByID(int id) {
        String sql = "SELECT * FROM clients WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Client found");
                    return new Client(
                            rs.getInt("consultant_id"),
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("note"));
                } else {
                    System.out.println("Client not found");
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return null;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            int counter = 0;
            while (rs.next()) {

                Client client = new Client(
                        rs.getInt("consultant_id"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("note"));

                clients.add(client);
                counter++;
            }
            System.out.println(counter + " clients added to the list successfully.");
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return clients;
    }

    public void ClientsTablePrint() {
        String sql = "SELECT * FROM clients";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.printf("%-5s %-15s %-20s %-20s %-30s %-40s %-10s\n",
                    "ID", "|Consultant ID", "|Name", "|Phone Number", "|Email", "|Address", "|Note");
            System.out.println("------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(" %-5s %-15s %-20s %-20s %-30s %-39s %-10s\n",
                        resultSet.getString("id"),
                        resultSet.getString("consultant_id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        "|" + resultSet.getString("note"));
            }

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("The client was deleted successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public List<Client> searchClientByName(String nameSearch) {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, "%" + nameSearch + "%");

            try (ResultSet rs = statement.executeQuery()) {
                int counter = 0;
                while (rs.next()) {
                    Client client = new Client(
                            rs.getInt("consultant_id"),
                            rs.getString("name"),
                            rs.getString("phone_number"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getString("note"));
                    clients.add(client);
                    counter++;
                }
                System.out.println(counter + " clients found.");

            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return clients;
    }
}
