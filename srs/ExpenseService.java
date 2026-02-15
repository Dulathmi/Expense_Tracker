package srs;
import java.io.*;
import java.util.ArrayList;

public class ExpenseService {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private int nextId = 1;
    private final String filePath;

    public ExpenseService(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    public void addExpense(String date, String category, String note, double amount) {
        Expense e = new Expense(nextId++, date, category, note, amount);
        expenses.add(e);
        saveToFile();
    }

    public void viewAll() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        for (Expense e : expenses) System.out.println(e);
    }

    public boolean deleteById(int id) {
        Expense target = null;
        for (Expense e : expenses) {
            if (e.getId() == id) { target = e; break; }
        }
        if (target != null) {
            expenses.remove(target);
            saveToFile();
            return true;
        }
        return false;
    }

    public void totalByCategory(String category) {
        double total = 0;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) total += e.getAmount();
        }
        System.out.println("Total for " + category + ": Rs " + total);
    }

    public void totalAll() {
        double total = 0;
        for (Expense e : expenses) total += e.getAmount();
        System.out.println("Total expenses: Rs " + total);
    }

    private void loadFromFile() {
        File f = new File(filePath);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int maxId = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Expense e = Expense.fromCsv(line);
                expenses.add(e);
                if (e.getId() > maxId) maxId = e.getId();
            }
            nextId = maxId + 1;
        } catch (Exception ex) {
            System.out.println("Failed to load file: " + ex.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Expense e : expenses) pw.println(e.toCsv());
        } catch (Exception ex) {
            System.out.println("Failed to save file: " + ex.getMessage());
        }
    }
}



