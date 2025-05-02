package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Artist {
    private String name;
    private String genre;
    private List<String> eventHistory; // Lista de nombres/fechas de eventos

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.eventHistory = new ArrayList<>();
    }

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public List<String> getEventHistory() { return eventHistory; }

    // Add event to history
    public void addEventToHistory(Event event) {
        String eventDetails = event.getName() + " (" + event.getDate() + ")";
        eventHistory.add(eventDetails);
    }

    // Show event history
    public void showEventHistory() {
        System.out.println("Historial de eventos de " + name + ":");
        if (eventHistory.isEmpty()) {
            System.out.println("Sin eventos previos.");
        } else {
            for (String info : eventHistory) {
                System.out.println("- " + info);
            }
        }
    }

    // Create an artist from the console
    public static Artist createArtist(Scanner scanner) {
        System.out.println("---- Crear nuevo artista ----");

        System.out.print("Nombre del artista: ");
        String name = scanner.nextLine();

        System.out.print("Género musical: ");
        String genre = scanner.nextLine();

        return new Artist(name, genre);
    }

    // Single method to add event to history
    public void addEvent(Event event) {
        addEventToHistory(event); // call the method that adds to history
    }
}




