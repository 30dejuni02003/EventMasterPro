package org.example;

import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Location> locations = new ArrayList<>();
        ArrayList<Artist> artists = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Attendee> attendees = new ArrayList<>();

        boolean running = true;
        while (running) {
            try {
                System.out.println("\n---- EventMaster Pro ----");
                System.out.println("1. Create Location");
                System.out.println("2. Create Artist");
                System.out.println("3. Managing Events");
                System.out.println("4. Managing Tickets");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        locations.add(Location.createLocation(scanner));
                        System.out.println("Location created successfully.");
                        break;

                    case 2:
                        artists.add(Artist.createArtist(scanner));
                        System.out.println("Artist created successfully.");
                        break;

                    case 3:
                        manageEvents(scanner, locations, artists, events);
                        break;

                    case 4:
                        manageTickets(scanner, events, tickets, attendees);
                        break;

                    case 5:
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear buffer
            }
        }
    }

    private static void manageEvents(Scanner scanner, List<Location> locations, List<Artist> artists, ArrayList<Event> events) {
        boolean managingEvents = true;

        while (managingEvents) {
            System.out.println("\n---- Event Management ----");
            System.out.println("1. Create Event");
            System.out.println("2. Modify Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Assign Artist to Event");
            System.out.println("5. Show Artists in Event");
            System.out.println("6. Show Event");
            System.out.println("7. Financial Summary");
            System.out.println("8. Back to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (locations.isEmpty()) {
                        System.out.println("No locations available.");
                        break;
                    }
                    for (int i = 0; i < locations.size(); i++) {
                        System.out.println(i + ". " + locations.get(i).getName());
                    }
                    System.out.print("Select location index: ");
                    int locIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (locIndex >= 0 && locIndex < locations.size()) {
                        // Corregido: pasamos 'events' como un ArrayList<Event>
                        Event newEvent = Event.createEvent(scanner, locations.get(locIndex), events);
                        events.add(newEvent);
                        locations.get(locIndex).bookDate(newEvent.getDate());
                        System.out.println("Event created successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 2:
                    if (events.isEmpty()) {
                        System.out.println("No events to modify.");
                        break;
                    }
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(i + ". " + events.get(i).getName());
                    }
                    int modIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (modIndex >= 0 && modIndex < events.size()) {
                        Event e = events.get(modIndex);
                        System.out.println("1. Change Name");
                        System.out.println("2. Change Category");
                        System.out.println("3. Change Time");
                        System.out.print("Choose: ");
                        int opt = scanner.nextInt();
                        scanner.nextLine();
                        switch (opt) {
                            case 1 -> {
                                System.out.print("New name: ");
                                e.setName(scanner.nextLine());
                            }
                            case 2 -> {
                                System.out.print("New category: ");
                                e.setCategory(scanner.nextLine());
                            }
                            case 3 -> {
                                System.out.print("New time (HH:mm): ");
                                e.setTime(LocalTime.parse(scanner.nextLine()));
                            }
                            default -> System.out.println("Invalid option.");
                        }
                        System.out.println("Event modified.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 3:
                    if (events.isEmpty()) {
                        System.out.println("No events to delete.");
                        break;
                    }
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(i + ". " + events.get(i).getName());
                    }
                    int delIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (delIndex >= 0 && delIndex < events.size()) {
                        events.get(delIndex).deleteEvent();
                        events.remove(delIndex);
                        System.out.println("Event deleted.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 4:
                    if (events.isEmpty() || artists.isEmpty()) {
                        System.out.println("No events or artists.");
                        break;
                    }
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(i + ". " + events.get(i).getName());
                    }
                    int evIndex = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < artists.size(); i++) {
                        System.out.println(i + ". " + artists.get(i).getName());
                    }
                    int artIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (evIndex >= 0 && evIndex < events.size() &&
                            artIndex >= 0 && artIndex < artists.size()) {
                        Artist artist = artists.get(artIndex);
                        events.get(evIndex).addArtist(artist);
                        artist.addEvent(events.get(evIndex)); // ← Añadido a historial
                        System.out.println("Artist assigned.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 5:
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(i + ". " + events.get(i).getName());
                    }
                    System.out.print("Event index: ");
                    int idx = scanner.nextInt();
                    scanner.nextLine();
                    if (idx >= 0 && idx < events.size()) {
                        events.get(idx).showArtists();
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 6:
                    for (int i = 0; i < events.size(); i++) {
                        Event e = events.get(i);
                        System.out.println(i + ". " + e.getName() + " | " +
                                e.getCategory() + " | " +
                                e.getDate() + " | " +
                                e.getTime() + " | " +
                                e.getLocation().getName());
                    }
                    break;

                case 7:
                    if (events.isEmpty()) {
                        System.out.println("No events.");
                        break;
                    }
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(i + ". " + events.get(i).getName());
                    }
                    System.out.print("Event index: ");
                    int finIdx = scanner.nextInt();
                    scanner.nextLine();
                    if (finIdx >= 0 && finIdx < events.size()) {
                        FinancialRecord fr = events.get(finIdx).getFinancialRecord();
                        System.out.println("Estimated Income: $" + fr.getEstimatedIncome());
                        System.out.println("Estimated Expenses: $" + fr.getEstimatedExpenses());
                        System.out.println("Actual Income: $" + fr.getActualIncome());
                        System.out.println("Actual Expenses: $" + fr.getActualExpenses());
                        System.out.println("Profit: $" + fr.getProfit());
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 8:
                    managingEvents = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageTickets(Scanner scanner, List<Event> events, List<Ticket> tickets, List<Attendee> attendees) {
        boolean managingTickets = true;

        while (managingTickets) {
            System.out.println("\n---- Ticket Management ----");
            System.out.println("1. Create Ticket");
            System.out.println("2. Register Sale");
            System.out.println("3. Validate Ticket on Entry");
            System.out.println("4. Show All Tickets");
            System.out.println("5. Attendance Statistics");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1:
                    if (events.isEmpty()) {
                        System.out.println("No events.");
                        break;
                    }
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println(i + ". " + events.get(i).getName());
                    }
                    System.out.print("Event index: ");
                    int evIndex = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ticket type: ");
                    String type = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stock: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    Ticket newTicket = new Ticket(type, price, stock, events.get(evIndex));
                    tickets.add(newTicket);
                    System.out.println("Ticket created.");
                    break;

                case 2:
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets.");
                        break;
                    }
                    for (int i = 0; i < tickets.size(); i++) {
                        System.out.println(i + ". " + tickets.get(i).getTicketType());
                    }
                    System.out.print("Ticket index: ");
                    int tixIdx = scanner.nextInt();
                    scanner.nextLine();
                    Ticket tix = tickets.get(tixIdx);

                    System.out.print("Quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine();

                    if (tix.sellTicket(qty)) {
                        tix.getEvent().addIncome(qty * tix.getPrice());
                        for (int i = 0; i < qty; i++) {
                            System.out.print("Attendee name #" + (i + 1) + ": ");
                            String name = scanner.nextLine();
                            String code = UUID.randomUUID().toString();
                            Attendee attendee = new Attendee(name, code, tix);
                            attendees.add(attendee);
                            System.out.println("Entry code: " + code);
                        }
                    } else {
                        System.out.println("Not enough stock.");
                    }
                    break;

                case 3:
                    if (attendees.isEmpty()) {
                        System.out.println("No attendees.");
                        break;
                    }
                    System.out.print("Enter entry code: ");
                    String inputCode = scanner.nextLine();
                    boolean found = false;
                    for (Attendee a : attendees) {
                        if (a.validateEntry(inputCode)) {
                            System.out.println("Welcome, " + a.getName() + "!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Invalid code.");
                    }
                    break;

                case 4:
                    for (Ticket t : tickets) {
                        System.out.println(t);
                    }
                    break;

                case 5:
                    System.out.println("Attendance statistics are not yet implemented.");
                    break;

                case 6:
                    managingTickets = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}





