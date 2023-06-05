package dev.monczall.itconference.controller.mapper;

import dev.monczall.itconference.controller.model.AttendeeDto;
import dev.monczall.itconference.model.Attendee;

import java.util.List;

public class AttendeeDtoMapper {

    public static List<AttendeeDto> mapAttendeeToAttendeeDto(List<Attendee> attendees) {
        return attendees.stream()
                .map(attendee -> new AttendeeDto(attendee.getLogin(), attendee.getEmail()))
                .toList();
    }

}
