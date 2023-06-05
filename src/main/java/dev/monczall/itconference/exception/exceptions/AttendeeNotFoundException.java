package dev.monczall.itconference.exception.exceptions;

public class AttendeeNotFoundException extends RuntimeException {
    public AttendeeNotFoundException() {
        super("Attendee not found");
    }
}
