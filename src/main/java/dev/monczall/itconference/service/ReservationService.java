package dev.monczall.itconference.service;

import dev.monczall.itconference.controller.model.ReservationDto;
import dev.monczall.itconference.exception.exceptions.*;
import dev.monczall.itconference.model.Attendee;
import dev.monczall.itconference.model.Lecture;
import dev.monczall.itconference.model.Reservation;
import dev.monczall.itconference.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final AttendeeService attendeeService;

    private final LectureService lectureService;

    public List<Lecture> getUserLectures(String login) {
        Long attendeeId = attendeeService.getAttendeeIdByLogin(login);
        List<Long> lectureIds = reservationRepository.findAllLectureIdsByAttendeeId(attendeeId);
        List<Lecture> response = lectureService.getAllLecturesByIds(lectureIds);

        return response;
    }

    public ReservationDto reserveLecture(Long lectureId, String login, String email) {

        if (login.isBlank() || !attendeeService.isEmailValid(email)) {
            throw new MissingDataException();
        }

        if (getAttendantsCount(lectureId) >= 5) {
            throw new LectureAtFullCapacityException();
        }

        if (!attendeeService.checkAttendeeLoginAndEmailMatch(login, email)) {
            throw new AttendeeLoginAlreadyInUseException();
        }

        Attendee attendee;

        if (!attendeeService.checkAttendeeExistsByLogin(login)) {
            attendee = attendeeService.registerNewAttendee(login, email);
        } else {
            attendee = attendeeService.getAttendeeByLogin(login);
        }

        Lecture lecture = lectureService.getLectureById(lectureId);

        if(lecture == null) {
            throw new LectureNotFoundException();
        }

        if (isAttendeeBusy(login, lecture)) {
            throw new AttendeeIsBusyAtTheTimeException();
        }

        Reservation newReservation = new Reservation();
        newReservation.setLectureId(lectureId);
        newReservation.setAttendeeId(attendee.getId());

        Reservation reservation = reservationRepository.save(newReservation);

        System.out.println(reservation);

        String lectureName = lecture.getName();

        sendReservationSuccessfulEmail(email, reservation.getId(), lectureName);

        return new ReservationDto("Reservation successful", lectureName);
    }

    private boolean isAttendeeBusy(String login, Lecture newLecture) {
        List<Lecture> userLectures = getUserLectures(login);

        return userLectures.stream()
                .anyMatch(userLecture ->
                        newLecture.getStartTime().isBefore(userLecture.getEndTime()) &&
                                newLecture.getEndTime().isAfter(userLecture.getStartTime())
                );
    }

    private int getAttendantsCount(Long lectureId) {
        return reservationRepository.countByLectureId(lectureId);
    }

    private void sendReservationSuccessfulEmail(String email, Long reservationId, String lectureName) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        DateTimeFormatter emailFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


        String fileName = "reservation-" + reservationId + "-" + email.substring(0, email.indexOf('@')) + "-"
                + date.format(format) + ".txt";

        new File(fileName);

        try {
            PrintWriter pw = new PrintWriter(fileName);
            pw.println("Date: " + date.format(emailFormat));
            pw.println("Receiver: " + email);
            pw.println("Reservation for: " + lectureName + " successful.");
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
