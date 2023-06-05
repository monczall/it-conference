package dev.monczall.itconference.exception.exceptions;

public class AttendeeIsBusyAtTheTimeException extends RuntimeException {
    public AttendeeIsBusyAtTheTimeException() {
        super("Attendee is already participating in other lecture at the time");
    }
}
