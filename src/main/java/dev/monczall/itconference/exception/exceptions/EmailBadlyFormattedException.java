package dev.monczall.itconference.exception.exceptions;

public class EmailBadlyFormattedException extends RuntimeException {
    public EmailBadlyFormattedException() {
        super("Email address badly formatted");
    }
}
