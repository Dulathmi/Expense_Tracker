package srs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ExpenseService service = new ExpenseService("expenses.csv");

        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add expense");
            System.out.println("2. View all");
            System.out.println("3. Delete by ID");
            System.out.println("4. Total by category");
            System.out.println("5. Total all");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    System.out.print("Category (Food/Travel/etc): ");
                    String category = sc.nextLine();

                    System.out.print("Note: ");
                    String note = sc.nextLine();

                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    service.addExpense(date, category, note, amount);
                    System.out.println("Added!");
                    break;

                case 2:
                    service.viewAll();
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println(service.deleteById(id) ? "Deleted!" : "Not found!");
                    break;

                case 4:
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    service.totalByCategory(cat);
                    break;

                case 5:
                    service.totalAll();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}

