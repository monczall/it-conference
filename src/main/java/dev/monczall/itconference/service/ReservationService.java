package dev.monczall.itconference.service;

import dev.monczall.itconference.controller.model.ReservationDto;
import dev.monczall.itconference.exception.exceptions.AttendeeLoginAlreadyInUseException;
import dev.monczall.itconference.exception.exceptions.LectureAtFullCapacityException;
import dev.monczall.itconference.exception.exceptions.MissingDataException;
import dev.monczall.itconference.model.Attendee;
import dev.monczall.itconference.model.Reservation;
import dev.monczall.itconference.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final AttendeeService attendeeService;

    private final LectureService lectureService;

    public ReservationDto reserveLecture(Long lectureId, String login, String email) {

        if(login.isBlank() || !isEmailValid(email)) {
            throw new MissingDataException("Please fill all required data");
        }

        if(getAttendantsCount(lectureId) >= 5) {
            throw new LectureAtFullCapacityException("There are no more free spots in this lecture");
        }

        if(!attendeeService.checkAttendeeLoginAndEmailMatch(login, email)) {
            throw new AttendeeLoginAlreadyInUseException("Attendee with given login already exists");
        }

        Attendee attendee;

        if(!attendeeService.checkAttendeeExistsByLogin(login)) {
            attendee = attendeeService.registerNewAttendee(login, email);
        } else {
            attendee = attendeeService.getAttendeeByLogin(login);
        }

        Reservation newReservation = new Reservation();
        newReservation.setLectureId(lectureId);
        newReservation.setAttendeeId(attendee.getId());

        Reservation reservation = reservationRepository.save(newReservation);

        System.out.println(reservation);

        String lectureName = lectureService.getLectureNameById(lectureId);


        sendReservationSuccessfulEmail(email, reservation.getId(), lectureName);

        return new ReservationDto("Reservation successful", lectureName);
    }

    private int getAttendantsCount(Long lectureId) {
        return reservationRepository.countByLectureId(lectureId);
    }

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void sendReservationSuccessfulEmail(String email, Long reservationId, String lectureName)  {
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
