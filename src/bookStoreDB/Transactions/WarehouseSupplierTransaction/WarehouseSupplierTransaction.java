package bookStoreDB.Transactions.WarehouseSupplierTransaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class WarehouseSupplierTransaction {
    private int id;                     // ID of the transaction
    private int bookId;                // ID of the book
    private int supplierId;             // ID of the supplier
    private int quantity;               // Quantity supplied
    private BigDecimal supplyPrice;     // Supply price
    private Timestamp supplyDate;       // Date of supply
    private String note;                // Note about the transaction

    // Default constructor
    public WarehouseSupplierTransaction() {
    }

    // Parameterized constructor
    public WarehouseSupplierTransaction(int bookId, int supplierId, int quantity, BigDecimal supplyPrice, Timestamp supplyDate, String note) {
        this.bookId = bookId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
        this.supplyDate = supplyDate;
        this.note = note;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Timestamp getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Timestamp supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
