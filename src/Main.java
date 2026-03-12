import service.AnalyticsService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // Terminal Colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {

        while (true) {

            System.out.println(CYAN + "\n========== FINANCE MANAGEMENT SYSTEM ==========" + RESET);
            System.out.println(YELLOW + "1." + RESET + " Register User");
            System.out.println(YELLOW + "2." + RESET + " Login");
            System.out.println(YELLOW + "3." + RESET + " Add Transaction");
            System.out.println(YELLOW + "4." + RESET + " Category Summary");
            System.out.println(YELLOW + "5." + RESET + " Monthly Summary");
            System.out.println(YELLOW + "6." + RESET + " Transaction History (Account)");
            System.out.println(YELLOW + "7." + RESET + " Transactions by Category");
            System.out.println(YELLOW + "8." + RESET + " Transactions by Date Range");
            System.out.println(YELLOW + "9." + RESET + " Category Breakdown");
            System.out.println(YELLOW + "10." + RESET + " Exit");

            System.out.print(BLUE + "Choose option: " + RESET);

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(RED + "Invalid input. Enter a number." + RESET);
                continue;
            }

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    addTransaction();
                    break;

                case 4:
                    AnalyticsService.categorySummary();
                    break;

                case 5:
                    AnalyticsService.monthlySummary();
                    break;

                case 6:
                    transactionHistory();
                    break;

                case 7:
                    categoryTransactions();
                    break;

                case 8:
                    dateFilter();
                    break;

                case 9:
                    AnalyticsService.categoryBreakdown();
                    break;

                case 10:
                    System.out.println(GREEN + "Exiting system..." + RESET);
                    System.exit(0);

                default:
                    System.out.println(RED + "Invalid option. Try again." + RESET);
            }
        }
    }

    private static void register() {

        System.out.print(YELLOW + "Enter username: " + RESET);
        String username = sc.nextLine();

        System.out.print(YELLOW + "Enter password: " + RESET);
        String password = sc.nextLine();

        UserService.register(username, password);

        System.out.println(GREEN + "User registered successfully!" + RESET);
    }

    private static void login() {

        System.out.print(YELLOW + "Username: " + RESET);
        String username = sc.nextLine();

        System.out.print(YELLOW + "Password: " + RESET);
        String password = sc.nextLine();

        boolean success = UserService.login(username, password);

        if (success)
            System.out.println(GREEN + "Login successful!" + RESET);
        else
            System.out.println(RED + "Login failed!" + RESET);
    }

    private static void addTransaction() {

        try {
            System.out.print(YELLOW + "User ID: " + RESET);
            int userId = Integer.parseInt(sc.nextLine());

            System.out.print(YELLOW + "Account ID: " + RESET);
            int accountId = Integer.parseInt(sc.nextLine());

            showCategories();

            System.out.print(YELLOW + "Category ID: " + RESET);
            int categoryId = Integer.parseInt(sc.nextLine());

            System.out.print(YELLOW + "Amount (₹): " + RESET);
            double amount = Double.parseDouble(sc.nextLine());

            System.out.print(YELLOW + "Type (INCOME / EXPENSE): " + RESET);
            String type = sc.nextLine().toUpperCase();

            if (!type.equals("INCOME") && !type.equals("EXPENSE")) {
                System.out.println(RED + "Invalid type!" + RESET);
                return;
            }

            System.out.print(YELLOW + "Description: " + RESET);
            String desc = sc.nextLine();

            TransactionService.addTransaction(
                    userId,
                    accountId,
                    categoryId,
                    amount,
                    type,
                    desc
            );

            System.out.println(GREEN + "Transaction Added Successfully!" + RESET);

        } catch (Exception e) {

            System.out.println(RED + "Invalid input. Please try again." + RESET);
        }
    }

    private static void transactionHistory() {

        try {

            System.out.print(YELLOW + "Enter Account ID: " + RESET);
            int accountId = Integer.parseInt(sc.nextLine());

            AnalyticsService.transactionHistory(accountId);

        } catch (Exception e) {

            System.out.println(RED + "Invalid account ID." + RESET);
        }
    }

    private static void categoryTransactions() {

        try {

            showCategories();

            System.out.print(YELLOW + "Enter Category ID: " + RESET);
            int categoryId = Integer.parseInt(sc.nextLine());

            AnalyticsService.categoryTransactions(categoryId);

        } catch (Exception e) {

            System.out.println(RED + "Invalid category ID." + RESET);
        }
    }

    private static void dateFilter() {

        try {

            System.out.print(YELLOW + "Start Date (YYYY-MM-DD): " + RESET);
            String start = sc.nextLine();

            System.out.print(YELLOW + "End Date (YYYY-MM-DD): " + RESET);
            String end = sc.nextLine();

            AnalyticsService.transactionsByDate(start, end);

        } catch (Exception e) {

            System.out.println(RED + "Invalid date format." + RESET);
        }
    }

    private static void showCategories() {

        System.out.println(CYAN + "\nAvailable Categories:" + RESET);
        System.out.println("1 → Food");
        System.out.println("2 → Transport");
        System.out.println("3 → Rent");
        System.out.println("4 → Salary");
        System.out.println("5 → Entertainment");
    }
}