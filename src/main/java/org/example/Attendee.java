package org.example;

import java.time.LocalDateTime;

public class Attendee {
    private String name;
    private String entryCode;
    private boolean isValidated;
    private LocalDateTime entryTime;
    private Ticket ticket;

    public Attendee(String name, String entryCode, Ticket ticket) {
        this.name = name;
        this.entryCode = entryCode;
        this.ticket = ticket;
        this.isValidated = false;
    }

    public String getName() {
        return name;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean validateEntry(String code) {
        if (this.entryCode.equals(code) && !isValidated) {
            this.isValidated = true;
            this.entryTime = LocalDateTime.now();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Asistente: " + name + " | Evento: " + ticket.getEvent().getName() + " | Entrada: " + ticket.getTicketType();
    }
}

