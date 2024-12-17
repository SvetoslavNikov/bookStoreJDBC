package bookStoreDB.Transactions.ConsultantClientTransactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

public class ConsultantClientTransaction {
    private int id;
    private int bookId;
    private int consultantId;
    private int clientId;
    private Timestamp saleDate;
    private int quantity;
    private BigDecimal totalPrice;
    private String notes;

    // Default constructor
    public ConsultantClientTransaction() {
    }

    // Parameterized constructor
    public ConsultantClientTransaction(int bookId, int consultantId, int clientId, Timestamp saleDate, int quantity, BigDecimal totalPrice, String notes) {
        this.bookId = bookId;
        this.consultantId = consultantId;
        this.clientId = clientId;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

