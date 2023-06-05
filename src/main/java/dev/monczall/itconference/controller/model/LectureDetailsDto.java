package dev.monczall.itconference.controller.model;

public record LectureDetailsDto(String lectureName, int attendeesCount, int attendeesLimit, float percentOfCapacity,
                                float percentOfAllAttendees) {
}
