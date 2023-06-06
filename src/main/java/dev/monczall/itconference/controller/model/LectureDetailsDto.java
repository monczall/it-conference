package dev.monczall.itconference.controller.model;

public record LectureDetailsDto(String lectureName, int attendeesCount, int attendeesLimit, String percentOfCapacity,
                                String percentOfAllAttendees) {
}
