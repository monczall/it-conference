package dev.monczall.itconference.controller;

import dev.monczall.itconference.controller.model.AttendeeDto;
import dev.monczall.itconference.controller.model.ReservationDto;
import dev.monczall.itconference.model.Lecture;
import dev.monczall.itconference.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/{login}")
    public List<Lecture> getUserLectures(@PathVariable String login) {
        return reservationService.getUserLectures(login);
    }

    @PostMapping("/{lectureId}/")
    public ReservationDto reserveLecture(@PathVariable Long lectureId, @RequestBody AttendeeDto attendeeDto) {
        return reservationService.reserveLecture(lectureId, attendeeDto.login(), attendeeDto.email());
    }

}
