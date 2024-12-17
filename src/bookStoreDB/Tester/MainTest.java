package bookStoreDB.Tester;

import bookStoreDB.books.Book;
import bookStoreDB.books.BookDAO;
import bookStoreDB.clients.Client;
import bookStoreDB.clients.ClientDAO;
import bookStoreDB.consultants.Consultant;
import bookStoreDB.consultants.ConsultantDAO;
import bookStoreDB.suppliers.Supplier;
import bookStoreDB.suppliers.SupplierDAO;

import java.math.BigDecimal;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        testBookDAO();
        testClientDAO();
        testConsultantDAO();
        testSupplierDAO();
    }

    private static void testBookDAO() {
        BookDAO bookDAO = new BookDAO();


        Book testBook = new Book("Test Title", "Test Author", new BigDecimal("9.99"), new BigDecimal("5.99"), 10, 1, "Test Note");
        bookDAO.addBook(testBook);
        System.out.println("Add Book Test Complete.");


        List<Book> books = bookDAO.getAllBooks();
        int lastBookId = books.get(books.size() - 1).getId();


        Book retrievedBook = bookDAO.getBookById(lastBookId);
        System.out.println("Retrieved Book: " + retrievedBook);


        testBook.setTitle("Updated Test Title");
        bookDAO.updateBook(lastBookId, testBook);
        System.out.println("Update Book Test Complete.");


        List<Book> foundBooks = bookDAO.searchBooksByTitle("Updated Test Title");
        System.out.println("Books found with title 'Updated Test Title': " + foundBooks.size());


        bookDAO.BookTablePrint();


        bookDAO.deleteBook(lastBookId);
        System.out.println("Delete Book Test Complete.\n");
    }

    private static void testClientDAO() {
        ClientDAO clientDAO = new ClientDAO();


        Client testClient = new Client(1, "Test Client", "1234567890", "testclient@example.com", "123 Test St", "Test Note");
        clientDAO.addClient(testClient);
        System.out.println("Add Client Test Complete.");


        List<Client> clients = clientDAO.getAllClients();
        int lastClientId = clients.get(clients.size() - 1).getId();


        Client retrievedClient = clientDAO.getClientByID(lastClientId);
        System.out.println("Retrieved Client: " + retrievedClient);


        testClient.setName("Updated Test Client");
        clientDAO.updateClient(lastClientId, testClient);
        System.out.println("Update Client Test Complete.");


        List<Client> foundClients = clientDAO.searchClientByName("Updated Test Client");
        System.out.println("Clients found with name 'Updated Test Client': " + foundClients.size());


        clientDAO.ClientsTablePrint();


        clientDAO.deleteClient(lastClientId);
        System.out.println("Delete Client Test Complete.\n");
    }

    private static void testConsultantDAO() {
        ConsultantDAO consultantDAO = new ConsultantDAO();


        Consultant testConsultant = new Consultant("Test Consultant", "1234567890", "testconsultant@example.com", "456 Test Ave", "Test Note");
        consultantDAO.addConsultant(testConsultant);
        System.out.println("Add Consultant Test Complete.");


        List<Consultant> consultants = consultantDAO.getAllConsultants();
        int lastConsultantId = consultants.get(consultants.size() - 1).getId();


        Consultant retrievedConsultant = consultantDAO.getConsultantById(lastConsultantId);
        System.out.println("Retrieved Consultant: " + retrievedConsultant);


        testConsultant.setName("Updated Test Consultant");
        consultantDAO.updateConsultant(lastConsultantId, testConsultant);
        System.out.println("Update Consultant Test Complete.");


        List<Consultant> foundConsultants = consultantDAO.searchConsultantsByName("Updated Test Consultant");
        System.out.println("Consultants found with name 'Updated Test Consultant': " + foundConsultants.size());


        consultantDAO.printConsultantsTable();


        consultantDAO.deleteConsultant(lastConsultantId);
        System.out.println("Delete Consultant Test Complete.\n");
    }

    private static void testSupplierDAO() {
        SupplierDAO supplierDAO = new SupplierDAO();


        Supplier testSupplier = new Supplier("Test Supplier", "1234567890", "testsupplier@example.com", "789 Test Blvd", "Test Note");
        supplierDAO.addSupplier(testSupplier);
        System.out.println("Add Supplier Test Complete.");


        List<Supplier> suppliers = supplierDAO.getAllSuppliers();
        int lastSupplierId = suppliers.get(suppliers.size() - 1).getId();


        Supplier retrievedSupplier = supplierDAO.getSupplierById(lastSupplierId);
        System.out.println("Retrieved Supplier: " + retrievedSupplier);


        testSupplier.setName("Updated Test Supplier");
        supplierDAO.updateSupplier(lastSupplierId, testSupplier);
        System.out.println("Update Supplier Test Complete.");


        List<Supplier> foundSuppliers = supplierDAO.searchSuppliersByName("Updated Test Supplier");
        System.out.println("Suppliers found with name 'Updated Test Supplier': " + foundSuppliers.size());


        supplierDAO.supplierTablePrint();


        supplierDAO.deleteSupplier(lastSupplierId);
        System.out.println("Delete Supplier Test Complete.\n");
    }
}
