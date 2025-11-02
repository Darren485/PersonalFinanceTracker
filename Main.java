
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.text.html.HTMLEditorKit;

public class Main {

    private static Scanner input;
    private static FinanceManager manager;
    private static FileHandler handler;
    private static boolean running = true;

    public static void main(String[] args) {
        System.out.println("FINANCE TRACKER");
        input = new Scanner(System.in);
        handler = new FileHandler();
        manager = handler.load();

        while (running) {
            showMenu();
            System.out.println("Choice: ");
            int choice;

            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("invalid choice. Enter a vaild option");
                continue;
            }
            handleChoice(choice);
        }
        input.close();
    }

    public static void showMenu() {
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View All Transactions");
        System.out.println("4. View Summary");
        System.out.println("5. Filter by Category");
        System.out.println("6. Filter by Month");
        System.out.println("7. Save and exit");
        System.out.println("8. Clear All Data");
    }

    public static void handleChoice(int choice) {
        switch (choice) {
            case 1 -> {//ADD INCOME
                System.out.println("Input Amount");
                BigDecimal amount = readBigDecimal();

                System.out.println("Input Category");
                String catString = input.nextLine().trim();
                Category category = parseCategory(catString);

                System.out.println("Input Description");
                String desc = input.nextLine();

                manager.addTransaction(LocalDate.now(), amount, TransactionType.INCOME, category, desc);
                System.out.println("Income added");
            }

            case 2 -> { //ADD EXPENSE
                System.out.println("Input Amount");
                BigDecimal amount = readBigDecimal();

                System.out.println("Input Category");
                String catString = input.nextLine().trim();
                Category category = parseCategory(catString);

                System.out.println("Input Description");
                String desc = input.nextLine();

                manager.addTransaction(LocalDate.now(), amount, TransactionType.EXPENSE, category, desc);
                System.out.println("Expense added");
            }

            case 3 -> {//VIEW ALL TRANSACTIONS
                List<Transaction> list = manager.getTransactions();
                if (list.isEmpty()) {
                    System.out.println("No transactions yet.");
                } else {
                    // Print header
                    System.out.println("ID   | Date         | Type     | Amount     | Category           | Description");
                    System.out.println("-".repeat(85));

                    // Print each transaction
                    list.forEach(System.out::println);
                }

            }
            case 4 -> {//VIEW SUMMARY
                System.out.println("Total Income: " + manager.getTotalIncome());
                System.out.println("Total Expense: " + manager.getTotalExpenses());
                System.out.println("Total Balance: " + manager.getBalance());
            }

            case 5 -> {//FILTER BY CATEGORY
                System.out.println("Enter the category");
                String catStr = input.nextLine().trim();
                Category category = parseCategory(catStr);

                List<Transaction> filtered = manager.filterByCategory(category);
                if (filtered.isEmpty()) {
                    System.out.println("No transactions for this category");
                } else {
                    filtered.forEach(System.out::println);
                }
            }

            case 6 -> {//FILTER BY MONTH
                try {
                    System.out.println("Input year (e.g) 2005");
                    int year = Integer.parseInt(input.nextLine().trim());
                    System.out.println("Input month(1-12)");
                    int month = Integer.parseInt(input.nextLine().trim());

                    List<Transaction> byMonth = manager.filterByMonth(year, month);
                    if (byMonth.isEmpty()) {
                        System.out.println("No transactions for this month");
                    } else {
                        byMonth.forEach(System.out::println);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid year/month");
                }
            }

            case 7 -> {//SAVE AND EXIT
                handler.save(manager);
                System.out.println("Saved. Exiting.");
                running = false;
            }

            case 8 -> {//CLEAR ALL DATA
                System.out.print("\u001B[31mAre you sure you want to delete ALL data? (yes/no): \u001B[0m");
                String confirm = input.nextLine().trim().toLowerCase();
                if (confirm.equals("yes") || confirm.equals("y")) {
                    manager.clear();
                    System.out.println("All data cleared.");
                } else {
                    System.out.println("Clear cancelled.");
                }
            }

            default ->
                System.out.println("Invalid choice");

        }
    }

    //HELPS TO READ THE BIGDECIMAL
    private static BigDecimal readBigDecimal() {
        while (true) {
            try {
                String line = input.nextLine();
                return new BigDecimal(line);
            } catch (Exception e) {
                System.out.println("Invalid amount. Try again");
            }
        }
    }

    //METHOD TO PARSE THE CATEGORY
    private static Category parseCategory(String input) {
        if (input == null || input.isEmpty()) {
            return Category.MISCELLANEOUS;
        }
        try {
            return Category.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Using MISCELLANEOUS");
            return Category.MISCELLANEOUS;

        }
    }
}
