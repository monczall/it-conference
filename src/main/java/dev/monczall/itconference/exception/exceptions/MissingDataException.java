package dev.monczall.itconference.exception.exceptions;

public class MissingDataException extends RuntimeException {
    public MissingDataException() {
        super("Please fill all required data");
    }
}
