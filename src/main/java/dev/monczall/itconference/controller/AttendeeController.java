package dev.monczall.itconference.controller;

import dev.monczall.itconference.controller.mapper.AttendeeDtoMapper;
import dev.monczall.itconference.controller.model.AttendeeDto;
import dev.monczall.itconference.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    public List<AttendeeDto> getAllAttendees() {
        return AttendeeDtoMapper.mapAttendeeToAttendeeDto(attendeeService.getAllAttendees());
    }

    @PutMapping
    public AttendeeDto updateAttendeeEmail(@RequestBody AttendeeDto attendeeDto) {
        return attendeeService.updateAttendeeEmail(attendeeDto);
    }

}
