package dev.monczall.itconference.controller;

import dev.monczall.itconference.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;


}
