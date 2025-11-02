
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private LocalDate date;
    private BigDecimal amount;
    private String description;
    private Category category;
    private TransactionType type;

    public Transaction(long id, LocalDate date, BigDecimal amount, String description, Category category, TransactionType type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.type = type;
    }

    // GET METHODS
    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format(
                "%-4d | %-12s | %-8s | $%-9.2f | %-18s | %s",
                id,
                date,
                type,
                type == TransactionType.EXPENSE ? amount.abs() : amount,
                category,
                description == null || description.isBlank() ? "-" : description
        );
    }
}
