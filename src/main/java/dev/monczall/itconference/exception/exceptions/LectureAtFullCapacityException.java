package dev.monczall.itconference.exception.exceptions;

public class LectureAtFullCapacityException extends RuntimeException {
    public LectureAtFullCapacityException(String message) {
        super(message);
    }
}
