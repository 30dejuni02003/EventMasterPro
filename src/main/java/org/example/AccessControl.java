package org.example;

import java.util.ArrayList;
import java.util.List;

public class AccessControl {
    private List<Attendee> attendees = new ArrayList<>();

    public boolean registerAttendee(Attendee attendee) {
        for (Attendee a : attendees) {
            if (a.getEntryCode().equals(attendee.getEntryCode())) {
                return false;
            }
        }
        attendees.add(attendee);
        return true;
    }

    public boolean validateEntry(String code, Event event) {
        for (Attendee attendee : attendees) {
            if (attendee.getTicket().getEvent().equals(event) && attendee.validateEntry(code)) {
                return true;
            }
        }
        return false;
    }

    public String generateAttendanceStats() {
        long validated = attendees.stream().filter(Attendee::isValidated).count();
        return "Total attendees: " + attendees.size() + ", Validated: " + validated;
    }

}


