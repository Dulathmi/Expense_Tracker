package srs;

public class Expense {
    private int id;
    private String date;
    private String category;
    private String note;
    private double amount;

    public Expense(int id, String date, String category, String note, double amount) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.note = note;
        this.amount = amount;
    }

    // --- NEW METHODS ADDED TO FIX THE ERRORS ---

    /**
     * Converts an Expense object into a single comma-separated line.
     * Used by ExpenseService.saveToFile()
     */
    public String toCsv() {
        return id + "," + date + "," + category + "," + note + "," + amount;
    }

    /**
     * Takes a CSV line and turns it back into an Expense object.
     * Used by ExpenseService.loadFromFile()
     */
    public static Expense fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        // Ensure the array has enough parts to prevent ArrayIndexOutOfBoundsException
        int id = Integer.parseInt(parts[0]);
        String date = parts[1];
        String category = parts[2];
        String note = parts[3];
        double amount = Double.parseDouble(parts[4]);
        
        return new Expense(id, date, category, note, amount);
    }

    // --- END OF NEW METHODS ---

    // Getters 
    public int getId() { return id; }
    public String getdate() { return date; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "ID: " + id + " | Date: " + date + " | Category: " + category + " | Note: " + note + " | Amount: " + amount;
    }
}