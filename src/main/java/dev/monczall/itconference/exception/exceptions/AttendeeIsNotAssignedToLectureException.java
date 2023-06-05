package dev.monczall.itconference.exception.exceptions;

public class AttendeeIsNotAssignedToLectureException extends RuntimeException {
    public AttendeeIsNotAssignedToLectureException() {
        super("Attendee is not assigned to this lecture");
    }
}
