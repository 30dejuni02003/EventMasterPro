package org.example;

public class FinancialRecord {
    private double estimatedIncome;
    private double estimatedExpenses;
    private double actualIncome;
    private double actualExpenses;

    public FinancialRecord(Budget budget) {
        this.estimatedIncome = budget.getEstimatedIncome();
        this.estimatedExpenses = budget.getEstimatedExpenses();
        this.actualIncome = 0;
        this.actualExpenses = 0;
    }

    public double getEstimatedIncome() {
        return estimatedIncome;
    }

    public double getEstimatedExpenses() {
        return estimatedExpenses;
    }

    public double getActualIncome() {
        return actualIncome;
    }

    public void addIncome(double amount) {
        this.actualIncome += amount;
    }

    public double getActualExpenses() {
        return actualExpenses;
    }

    public void addExpense(double amount) {
        this.actualExpenses += amount;
    }

    public double getProfit() {
        return actualIncome - actualExpenses;
    }

    public String generateFinancialReport() {
        return String.format(
                "Estimated Income: $%.2f\nEstimated Expenses: $%.2f\nActual Income: $%.2f\nActual Expenses: $%.2f\nProfit/Loss: $%.2f",
                estimatedIncome, estimatedExpenses, actualIncome, actualExpenses, getProfit()
        );
    }
}

