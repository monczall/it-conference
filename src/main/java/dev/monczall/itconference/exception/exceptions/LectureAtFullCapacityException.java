package dev.monczall.itconference.exception.exceptions;

public class LectureAtFullCapacityException extends RuntimeException {
    public LectureAtFullCapacityException() {
        super("There are no more free spots in this lecture");
    }
}
