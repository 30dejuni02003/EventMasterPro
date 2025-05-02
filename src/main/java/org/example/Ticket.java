package org.example;

public class Ticket {
    private String ticketType;
    private double price;
    private int stock;
    private Event event;
    private boolean isValidated;  // New attribute to control ticket validation

    // Constructor with event
    public Ticket(String ticketType, double price, int stock, Event event) {
        this.ticketType = ticketType;
        this.price = price;
        this.stock = stock;
        this.event = event;
        this.isValidated = false;  // Initially the ticket has not been validated

    }

    // Getters
    public String getTicketType() { return ticketType; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public Event getEvent() { return event; }

    // Setters
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setEvent(Event event) { this.event = event; }

    // Sell tickets
    public boolean sellTicket(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
            return true;
        }
        return false;
    }

    public void MarkasUsed() {
        if (!isValidated && stock > 0) {
            isValidated = true;
            stock--;  // Reducir el stock
            System.out.println("Ticket validated successfully.");
        } else if (isValidated) {
            System.out.println("This ticket has already been validated.");
        } else {
            System.out.println("This ticket is out of stock and cannot be validated.");
        }
    }

}




