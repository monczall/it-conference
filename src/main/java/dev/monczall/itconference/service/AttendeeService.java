package dev.monczall.itconference.service;

import dev.monczall.itconference.exception.exceptions.AttendeeNotFoundException;
import dev.monczall.itconference.model.Attendee;
import dev.monczall.itconference.model.Lecture;
import dev.monczall.itconference.model.Reservation;
import dev.monczall.itconference.repository.AttendeeRepository;
import dev.monczall.itconference.repository.LectureRepository;
import dev.monczall.itconference.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final ReservationRepository reservationRepository;
    private final LectureRepository lectureRepository;

    public List<Lecture> getUserLectures(String login) {
        Long attendeeId = getAttendeeIdByLogin(login);
        List<Long> lectureIds = reservationRepository.findAllLectureIdsByAttendeeId(attendeeId);
        List<Lecture> response = lectureRepository.findAllById(lectureIds);

        return response;
    }

    private Long getAttendeeIdByLogin(String login) {
        Attendee attendee = attendeeRepository.findByLogin(login).orElseThrow(
                () -> new AttendeeNotFoundException("Attendee not found")
        );

        return attendee.getId();
    }
}
