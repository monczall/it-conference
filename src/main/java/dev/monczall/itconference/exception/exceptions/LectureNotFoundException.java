package dev.monczall.itconference.exception.exceptions;

public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException() {
        super("Lecture not found");
    }
}
