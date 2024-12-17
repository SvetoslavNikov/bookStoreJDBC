package bookStoreDB.books;

import java.math.BigDecimal;

public class Book {
    private int id;
    private String title;
    private String author;
    private BigDecimal customerPrice;
    private BigDecimal supplyPrice;
    private int stock;
    private int supplierId;

    private String note;

    // Constructor
    public Book(String title, String author, BigDecimal customerPrice,
                BigDecimal supplyPrice, int stock, int supplierId, String note) {
        this.title = title;
        this.author = author;
        this.customerPrice = customerPrice;
        this.supplyPrice = supplyPrice;
        this.stock = stock;
        this.supplierId = supplierId;
        this.note = note;// Updated to int
    }

    public Book() {
    }



    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getCustomerPrice() {
        return customerPrice;
    }
    public void setCustomerPrice(BigDecimal customerPrice) {
        this.customerPrice = customerPrice;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }
    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


    public int getSupplierId() {
        return supplierId; // Updated to int
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getNote(){
        return this.note;
    }
    public void setNote(String note){
        this.note = note;
    }
}
