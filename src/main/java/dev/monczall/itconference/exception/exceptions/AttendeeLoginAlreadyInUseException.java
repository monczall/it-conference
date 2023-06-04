package dev.monczall.itconference.exception.exceptions;

public class AttendeeLoginAlreadyInUseException extends RuntimeException {
    public AttendeeLoginAlreadyInUseException(String message) {
        super(message);
    }
}
