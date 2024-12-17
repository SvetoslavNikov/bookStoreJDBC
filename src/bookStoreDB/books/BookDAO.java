package bookStoreDB.books;

import bookStoreDB.utils.DatabaseConnection;
import bookStoreDB.utils.SQLExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    //Book - DataAccessObject
    /*
        Methods:

        1. DatabaseConnection.getConnection() throws SQLException
            - Establishes a connection to the PostgresSQL database

        2. public void addBook(Book book)
            - Adds a new book to the database using an INSERT statement

        3. public void updateBook(int id, Book book)
            - Updates an existing book in the database based on the provided ID

        4. public Book getBookById(int id)
            - Retrieves a book from the database based on the provided ID

        5. public void BookTablePrint()
            - Prints all bookStoreDB.books from the database to the console

        6. public List<Book> getAllBooks()
            - Retrieves a list of all bookStoreDB.books from the database

        7. public void deleteBook(int id)
            - Deletes a book from the database based on the provided ID

        8. public List<Book> searchBooksByTitle(String titleSearch)
            - Searches for bookStoreDB.books in the database by title (case-insensitive)
    */


    public void addBook(Book book) {

        String sql = "INSERT INTO books (title, author, customer_price, supply_price, stock, supplier_id,note)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        //? ? ? ? ? are placeholder for the parameters we will use in the prepared statement

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(book, statement);
            statement.setString(7, book.getNote());


            statement.executeUpdate();
            System.out.println("The book was added successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public void updateBook(int id, Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, customer_price = ?, " +
                "supply_price = ?, stock = ?, supplier_id = ?, note = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            fillStatementParameters(book, statement);
            statement.setString(7, book.getNote());
            statement.setInt(8, id);

            statement.executeUpdate();
            System.out.println("The book was updated successfully!");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    private void fillStatementParameters(Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setBigDecimal(3, book.getCustomerPrice());
        statement.setBigDecimal(4, book.getSupplyPrice());
        statement.setInt(5, book.getStock());
        statement.setInt(6, book.getSupplierId());
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getBigDecimal("customer_price"),
                            rs.getBigDecimal("supply_price"),
                            rs.getInt("stock"),
                            rs.getInt("supplier_id"),
                            rs.getString("note")
                    );
                } else {
                    System.out.println("Book not found");
                }
            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {

            int counter = 0;
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBigDecimal("customer_price"),
                        rs.getBigDecimal("supply_price"),
                        rs.getInt("stock"),
                        rs.getInt("supplier_id"),
                        rs.getString("note")
                );
                books.add(book);
                counter++;
            }
            System.out.println(counter + " books added to the list successfully.");

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return books;
    }


    public void BookTablePrint() {
        String sql = "SELECT * FROM books";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {

            System.out.printf("%-5s %-20s %-20s %-15s %-15s %-10s %-15s %s\n",
                    "ID", "|Title", "|Author", "|Customer Price", "|Supply Price", "|Stock", "|Supplier ID", "|Note");
            System.out.println("------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf(" %-5s %-20s %-20s %-15s %-15s %-10s %-15s %s\n",
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("customer_price"),
                        resultSet.getString("supply_price"),
                        resultSet.getString("stock"),
                        resultSet.getString("supplier_id"),
                        resultSet.getString("note"));
            }

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }




    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
    }

    public List<Book> searchBooksByTitle(String titleSearch) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, "%" + titleSearch + "%");

            try (ResultSet rs = statement.executeQuery()) {
                int counter = 0;
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getBigDecimal("customer_price"),
                            rs.getBigDecimal("supply_price"),
                            rs.getInt("stock"),
                            rs.getInt("supplier_id"),
                            rs.getString("note")
                    );
                    books.add(book);
                    counter++;
                }
                System.out.println(counter + " bookStoreDB.books found.");

            }
        } catch (SQLException e) {
            SQLExceptionHandler.handleSQLException(e);
        }
        return books;
    }
}



