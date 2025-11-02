
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanceManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Transaction> transactions;
    private long nextId;

    public FinanceManager() {
        this.transactions = new ArrayList<>();
        this.nextId = 1L;
    }

    public void addTransaction(LocalDate date, BigDecimal amount, TransactionType type, Category category, String description) {
        Transaction transaction = new Transaction(
                nextId++,
                date,
                type == TransactionType.EXPENSE ? amount.negate() : amount,
                description,
                category,
                type 
        );
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> filterByCategory(Category category) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(category)) {
                filtered.add(transaction);
            }
        }
        return filtered;
    }

    public List<Transaction> filterByMonth(int year, int month) {
        List<Transaction> filter = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();
            if (date.getYear() == year && date.getMonthValue() == month) {
                filter.add(transaction);
            }
        }
        return filter;
    }

    public BigDecimal getTotalIncome() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    public BigDecimal getTotalExpenses() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                total = total.add(transaction.getAmount());
            }
        }
        return total.abs();
    }

    public BigDecimal getBalance() {
        return getTotalIncome().subtract(getTotalExpenses());
    }

    public Map<Category, BigDecimal> getExpenseByCategory() {
        Map<Category, BigDecimal> expenseMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                Category category = transaction.getCategory();
                BigDecimal amount = transaction.getAmount().abs();
                expenseMap.put(category, expenseMap.getOrDefault(category, BigDecimal.ZERO).add(amount));
            }
        }
        return expenseMap;
    }

    public Map<Integer, BigDecimal> getMonthlySummary(int year) {
        Map<Integer, BigDecimal> monthMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();
            if (date.getYear() == year) {
                int month = date.getMonthValue();
                BigDecimal amount = transaction.getAmount();
                monthMap.put(month, monthMap.getOrDefault(month, BigDecimal.ZERO).add(amount));
            }
        }
        return monthMap;
    }

    public void clear() {
        transactions.clear();
        nextId = 1L;
    }
}
