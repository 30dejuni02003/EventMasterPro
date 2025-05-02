package org.example;

public class Budget {
    private String event;
    private double estimatedExpenses;
    private double estimatedIncome;
    private double actualExpenses;
    private double actualIncome;

    public Budget(String event, double estimatedExpenses, double estimatedIncome) {
        this.event = event;
        this.estimatedExpenses = estimatedExpenses;
        this.estimatedIncome = estimatedIncome;
        this.actualExpenses = 0;
        this.actualIncome = 0;
    }

    // Getters
    public String getEvent() { return event; }
    public double getEstimatedExpenses() { return estimatedExpenses; }
    public double getEstimatedIncome() { return estimatedIncome; }
    public double getActualExpenses() { return actualExpenses; }
    public double getActualIncome() { return actualIncome; }

    // Setters
    public void setEstimatedExpenses(double estimatedExpenses) {
        if (estimatedExpenses < 0) throw new IllegalArgumentException("Estimated expenses cannot be negative.");
        this.estimatedExpenses = estimatedExpenses;
    }

    public void setEstimatedIncome(double estimatedIncome) {
        if (estimatedIncome < 0) throw new IllegalArgumentException("Estimated income cannot be negative.");
        this.estimatedIncome = estimatedIncome;
    }

    public void setActualExpenses(double actualExpenses) {
        if (actualExpenses < 0) throw new IllegalArgumentException("Actual expenses cannot be negative.");
        this.actualExpenses = actualExpenses;
    }

    public void setActualIncome(double actualIncome) {
        if (actualIncome < 0) throw new IllegalArgumentException("Actual income cannot be negative.");
        this.actualIncome = actualIncome;
    }

    // Calculations
    public double calculateEstimatedProfit() {
        return estimatedIncome - estimatedExpenses;
    }

    public double calculateActualProfit() {
        return actualIncome - actualExpenses;
    }

    public String compareEstimatedVsActual() {
        double profitDifference = calculateActualProfit() - calculateEstimatedProfit();
        return "Estimated Profit: $" + calculateEstimatedProfit() +
                ", Actual Profit: $" + calculateActualProfit() +
                ", Difference: $" + profitDifference;
    }

    @Override
    public String toString() {
        return "Budget for event: " + event +
                "\nEstimated Income: $" + estimatedIncome +
                "\nEstimated Expenses: $" + estimatedExpenses +
                "\nActual Income: $" + actualIncome +
                "\nActual Expenses: $" + actualExpenses +
                "\nEstimated Profit: $" + calculateEstimatedProfit() +
                "\nActual Profit: $" + calculateActualProfit();
    }
}
