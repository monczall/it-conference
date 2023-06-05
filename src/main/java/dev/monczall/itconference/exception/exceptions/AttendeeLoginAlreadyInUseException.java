package dev.monczall.itconference.exception.exceptions;

public class AttendeeLoginAlreadyInUseException extends RuntimeException {
    public AttendeeLoginAlreadyInUseException() {
        super("Attendee with given login already exists");
    }
}
