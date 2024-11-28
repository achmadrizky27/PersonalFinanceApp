package com.management.main;

import com.management.models.Transaction;
import com.management.services.FinanceService;
import com.management.reports.ReportGenerator;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        FinanceService service = new FinanceService();
        ReportGenerator reportGenerator = new ReportGenerator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Personal Finance Management ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter description: ");
                    String incomeDesc = scanner.nextLine();
                    service.addTransaction(new Transaction("Income", incomeAmount, incomeDesc));
                }
                case 2 -> {
                    System.out.print("Enter amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter description: ");
                    String expenseDesc = scanner.nextLine();
                    service.addTransaction(new Transaction("Expense", expenseAmount, expenseDesc));
                }
                case 3 -> {
                    System.out.println("Transactions:");
                    for (Transaction t : service.getTransactions()) {
                        System.out.println(t.getType() + ": " + t.getAmount() + " (" + t.getDescription() + ")");
                    }
                    System.out.println("Balance: " + service.getBalance());
                }
                case 4 -> reportGenerator.generateReport(service.getTransactions());
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
