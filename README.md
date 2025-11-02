# Personal Finance Tracker

A **clean, console-based personal finance manager** built in **Java** using **OOP principles**, `BigDecimal` for accurate money handling, and **object serialization** for persistent storage.

---

## Features

- Add **Income** and **Expense** entries
- Categorize transactions (Salary, Rent, Groceries, etc.)
- View all transactions in a **clean table format**
- **Filter** by:
  - Category
  - Month & Year
- **Summary**: Total Income, Total Expense, Balance
- **Auto-save** on exit
- **Auto-load** on startup
- Input validation & error handling

---

## Tech Stack

- **Java 17+**
- `java.time.LocalDate` – Date handling
- `java.math.BigDecimal` – Accurate financial calculations
- `ObjectOutputStream` / `ObjectInputStream` – Save/load data
- **OOP Design**: `model`, `service`, `storage` packages

---

## Project Structure

```
PersonalFinanceTracker/
├── src/
│   ├── model/
│   │   ├── Transaction.java
│   │   ├── TransactionType.java
│   │   └── Category.java
│   ├── service/
│   │   └── FinanceManager.java
│   ├── storage/
│   │   └── FileHandler.java
│   └── Main.java
├── data/
│   └── transaction.dat        ← Auto-saved data
├── README.md
└── .gitignore
```

---

## How to Run

```bash
# Compile
javac src/**/*.java

# Run
java src.Main
```

> Data is saved in `data/transaction.dat` and loaded automatically.

---

## Sample Output

```
FINANCE TRACKER
1. Add Income
2. Add Expense
3. View All Transactions
...
Choice: 3

ID   | Date         | Type     | Amount     | Category           | Description
-------------------------------------------------------------------------------------
1    | 2025-10-15   | INCOME   | $2500.00   | SALARY             | Monthly salary
2    | 2025-10-16   | EXPENSE  | $45.50     | GROCERIES          | Lunch at cafe
3    | 2025-10-18   | EXPENSE  | $1200.00   | RENT               | October rent

Total Income : $2500.00
Total Expense: $1245.50
Balance      : $1254.50
```

---

## Future Upgrades (Planned)

- [ ] **JDBC + MySQL** – Replace file storage
- [ ] **GUI** with JavaFX
- [ ] **Charts** (monthly spending)
- [ ] **Edit/Delete** transactions
- [ ] **CSV Export**

---

## Author

**ASUQUO BASSEY**  
[https://github.com/Darren485]
[https://x.com/darrenasuquo]

---
