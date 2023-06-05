package dev.monczall.itconference.controller;

import dev.monczall.itconference.controller.model.AttendeeDto;
import dev.monczall.itconference.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @PutMapping
    public AttendeeDto updateAttendeeEmail(@RequestBody AttendeeDto attendeeDto) {
        return attendeeService.updateAttendeeEmail(attendeeDto);
    }

}
