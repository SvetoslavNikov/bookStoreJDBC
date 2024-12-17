package bookStoreDB.Transactions.Finances;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FinancialTransaction {
    private int id;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;  // Balance after the transaction
    private String description;  // Description of the transaction
    private LocalDateTime transactionDate;  // Date and time of the transaction
    private String sourceType;  // Source type as a string
    private int sourceId;  // Changed from Long to int

    public enum TransactionType {
        INCOME, EXPENSE
    }

    public enum SourceType {
        CONSULTANT_CLIENT_TRANSACTIONS,  // Updated to match the required source type
        WAREHOUSE_SUPPLIER_TRANSACTIONS    // Updated to match the required source type
        // Add more as needed
    }

    // Constructor for new transactions (without id)
    public FinancialTransaction(TransactionType type, BigDecimal amount,
                                String description, SourceType sourceType, int sourceId) {
        // Validate type
        if (type != TransactionType.INCOME && type != TransactionType.EXPENSE) {
            throw new IllegalArgumentException("Transaction type must be INCOME or EXPENSE.");
        }
        // Validate source type
        if (sourceType != SourceType.CONSULTANT_CLIENT_TRANSACTIONS &&
                sourceType != SourceType.WAREHOUSE_SUPPLIER_TRANSACTIONS) {
            throw new IllegalArgumentException("Source type must be CONSULTANT_CLIENT_TRANSACTIONS or WAREHOUSE_SUPPLIER_TRANSACTIONS.");
        }

        this.type = type;
        this.amount = amount;
        this.description = description;
        this.sourceType = sourceType.name();
        this.sourceId = sourceId;
        this.transactionDate = LocalDateTime.now();  // Set transaction date to now
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}
