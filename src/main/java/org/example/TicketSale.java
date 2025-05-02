package org.example;

public class TicketSale {
    private Ticket ticket;
    private int quantitySold;

    public TicketSale(Ticket ticket) {
        this.ticket = ticket;
        this.quantitySold = 0;
    }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public int getQuantitySold() { return quantitySold; }
    public void setQuantitySold(int quantitySold) { this.quantitySold = quantitySold; }

    // Register ticket sales if there is sufficient stock
    public boolean registerSale(int quantity) {
        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive.");
            }
            if (ticket.sellTicket(quantity)) {
                quantitySold += quantity;
                return true;
            } else {
                System.out.println("Not enough tickets in stock.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Sales report
    public String getSalesReport() {
        return "Ticket Type: " + ticket.getTicketType() + " | Sold: " + quantitySold +
                " | Remaining Stock: " + ticket.getStock();
    }

    // Method to display the sale report for the ticket
    public void displaySaleReport() {
        System.out.println(getSalesReport());
    }
}


