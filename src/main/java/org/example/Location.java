package org.example;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.InputMismatchException;

public class Location {
     private String name;
     private String address;
     private int capacity;
     private boolean parking;
     private boolean withRoof;
     private Set<LocalDate> bookedDates;

     public Location(String name, boolean withRoof, boolean parking, int capacity, String address) {
          this.name = name;
          this.withRoof = withRoof;
          this.parking = parking;
          this.capacity = capacity;
          this.address = address;
          this.bookedDates = new HashSet<>();
     }

     // Getters and Setters
     public String getName() { return name; }
     public void setName(String name) { this.name = name; }

     public String getAddress() { return address; }
     public void setAddress(String address) { this.address = address; }

     public boolean hasParking() { return parking; }
     public void setParking(boolean parking) { this.parking = parking; }

     public int getCapacity() { return capacity; }
     public void setCapacity(int capacity) { this.capacity = capacity; }

     public boolean hasRoof() { return withRoof; }
     public void setWithRoof(boolean withRoof) { this.withRoof = withRoof; }

     public Set<LocalDate> getBookedDates() { return bookedDates; }
     public void setBookedDates(Set<LocalDate> bookedDates) { this.bookedDates = bookedDates; }

     // Create a new location from console input
     public static Location createLocation(Scanner scanner) {
          System.out.println("---- Create New Location ----");

          System.out.print("Location name: ");
          String name = scanner.nextLine();

          System.out.print("Address: ");
          String address = scanner.nextLine();

          int capacity = 0;
          while (true) {
               System.out.print("Capacity (number of people): ");
               try {
                    capacity = scanner.nextInt();
                    scanner.nextLine();
                    break;
               } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
               }
          }

          boolean parking = askYesNo(scanner, "Does it have parking? (yes/no): ");
          boolean withRoof = askYesNo(scanner, "Is it covered? (yes/no): ");

          return new Location(name, withRoof, parking, capacity, address);
     }

     private static boolean askYesNo(Scanner scanner, String message) {
          while (true) {
               System.out.print(message);
               String input = scanner.nextLine().trim().toLowerCase();
               if (input.equals("yes") || input.equals("y") || input.equals("true") || input.equals("si") || input.equals("sí") || input.equals("s")) {
                    return true;
               } else if (input.equals("no") || input.equals("n") || input.equals("false")) {
                    return false;
               } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
               }
          }
     }

     // Book a date
     public void bookDate(LocalDate date) {
          bookedDates.add(date);
     }

     // Check availability on a date
     public boolean isAvailableOnDate(LocalDate date) {
          return !bookedDates.contains(date);
     }

     // Show all booked dates
     public void showBookedDates() {
          System.out.println("Booked dates for " + name + ":");
          if (bookedDates.isEmpty()) {
               System.out.println("No dates booked.");
          } else {
               for (LocalDate date : bookedDates) {
                    System.out.println("- " + date);
               }
          }
     }
}




