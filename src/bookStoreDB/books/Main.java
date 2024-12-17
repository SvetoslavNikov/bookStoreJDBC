package bookStoreDB.books;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();

        Book newBook = new Book(
                "Java Programming1",
                "John Doe",
                new BigDecimal("29.99"),
                new BigDecimal("15.99"),
                100,
                1,
                ""
        );

        // Add the book to database
        //bookDAO.addBook(newBook);
        bookDAO.updateBook(2, newBook);
        // Retrieve a book
        Book retrievedBook = bookDAO.getBookById(7);
        if (retrievedBook != null) {
            System.out.println("Found book: " + retrievedBook.getTitle());
        }
         bookDAO.BookTablePrint();
    }
}