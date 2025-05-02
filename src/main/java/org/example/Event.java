package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Event {
    private String name;
    private String category;
    private LocalTime time;
    private LocalDate date;
    private Location location;
    private ArrayList<Artist> artists;
    private ArrayList<Ticket> tickets;
    private Budget budget;
    private AccessControl accessControl;
    private FinancialRecord financialRecord;

    public Event(String name, String category, LocalDate date, Location location, LocalTime time, Budget budget) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.location = location;
        this.time = time;
        this.artists = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.budget = budget;
        this.accessControl = new AccessControl();
        this.financialRecord = new FinancialRecord(budget);
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public ArrayList<Artist> getArtists() { return artists; }
    public void setArtists(ArrayList<Artist> artists) { this.artists = artists; }

    public ArrayList<Ticket> getTickets() { return tickets; }
    public void setTickets(ArrayList<Ticket> tickets) { this.tickets = tickets; }

    public Budget getBudget() { return budget; }
    public void setBudget(Budget budget) { this.budget = budget; }

    public FinancialRecord getFinancialRecord() { return financialRecord; }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void showTickets() {
        System.out.println("Tickets available for event: " + name);
        if (tickets.isEmpty()) {
            System.out.println("No tickets registered.");
        } else {
            for (Ticket ticket : tickets) {
                System.out.println("- Type: " + ticket.getTicketType() +
                        ", Price: $" + ticket.getPrice() +
                        ", Stock: " + ticket.getStock());
            }
        }
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.addEvent(this);
    }

    public boolean registerAttendee(Attendee attendee) {
        return accessControl.registerAttendee(attendee);
    }

    public boolean validateAttendeeEntry(String code) {
        return accessControl.validateEntry(code, this);
    }

    public String generateAttendanceStats() {
        return accessControl.generateAttendanceStats();
    }

    //  Method with duplication validation
    public static Event createEvent(Scanner scanner, Location location, ArrayList<Event> existingEvents) {
        System.out.println("---- Create an Event ----");

        System.out.print("Event name: ");
        String name = scanner.nextLine();

        System.out.print("Category of the event (concert, conference, etc.): ");
        String category = scanner.nextLine();

        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("Date of the event (format: yyyy-MM-dd): ");
                date = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date like 2025-05-01.");
            }
        }

        LocalTime time = null;
        while (time == null) {
            try {
                System.out.print("Time of the event (format: HH:mm): ");
                String timeInput = scanner.nextLine().trim();
                time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (Exception e) {
                System.out.println("Invalid time format. Please use HH:mm (e.g., 18:30).");
            }
        }

        // Duplicity validation
        for (Event existing : existingEvents) {
            if (existing.getDate().equals(date) &&
                    existing.getTime().equals(time) &&
                    existing.getLocation().equals(location)) {
                System.out.println("⚠️ Error: An event already exists at this location, date, and time.");
                return null;
            }
        }

        int budgetAmount = -1;
        while (budgetAmount < 0) {
            try {
                System.out.print("Event budget (must be non-negative): ");
                budgetAmount = scanner.nextInt();
                if (budgetAmount < 0) {
                    System.out.println("Budget cannot be negative. Please try again.");
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine();
            }
        }

        double estimatedExpenses = 0;
        double estimatedIncome = 0;
        boolean validBudget = false;

        while (!validBudget) {
            try {
                System.out.print("Estimated expenses: ");
                estimatedExpenses = scanner.nextDouble();
                System.out.print("Estimated income: ");
                estimatedIncome = scanner.nextDouble();
                scanner.nextLine();
                validBudget = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numeric values.");
                scanner.nextLine();
            }
        }

        Budget eventBudget = new Budget(name, estimatedExpenses, estimatedIncome);
        return new Event(name, category, date, location, time, eventBudget);
    }

    public void modifyEvent(Scanner scanner) {

    }

    public void deleteEvent() {
        this.name = null;
        this.category = null;
        this.date = null;
        this.location = null;
        this.time = null;
        this.artists.clear();
        System.out.println("Event deleted successfully.");
    }

    public void showArtists() {
        try {
            System.out.println("Artists performing in " + name + ":");
            if (artists == null || artists.isEmpty()) {
                System.out.println("No artists registered for this event.");
                return;
            }
            for (Artist artist : artists) {
                System.out.println("- " + artist.getName());
            }
        } catch (Exception e) {
            System.out.println("An error occurred while showing artists: " + e.getMessage());
        }
    }

    public void addIncome(double amount) {
        financialRecord.addIncome(amount);
    }

    public void addExpense(double amount) {
        financialRecord.addExpense(amount);
    }

    public void showFinancialStatus() {
        System.out.println(financialRecord.generateFinancialReport());
    }
}




