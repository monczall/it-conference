package dev.monczall.itconference.controller;

import dev.monczall.itconference.controller.model.ReservationDto;
import dev.monczall.itconference.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{lectureId}/")
    public ReservationDto reserveLecture(@PathVariable Long lectureId, @RequestParam String login, @RequestParam String email) {
        return reservationService.reserveLecture(lectureId, login, email);
    }

}
