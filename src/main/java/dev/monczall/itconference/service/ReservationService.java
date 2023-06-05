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

        Lecture lecture = lectureService.getLectureById(lectureId);

        if (login.isBlank() || !attendeeService.isEmailValid(email)) {
            throw new MissingDataException();
        }

        if (getAttendantsCount(lectureId) >= lecture.getCapacity()) {
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

        if (isAttendeeBusy(login, lecture)) {
            throw new AttendeeIsBusyAtTheTimeException();
        }

        Reservation newReservation = new Reservation();
        newReservation.setLectureId(lectureId);
        newReservation.setAttendeeId(attendee.getId());

        Reservation reservation = reservationRepository.save(newReservation);

        System.out.println(reservation);

        String lectureName = lecture.getName();

        sendReservationSuccessfulEmail(attendee, lecture, reservation.getId());

        return new ReservationDto("Reservation successful", lectureName);
    }

    public void deleteReservation(Long lectureId, String login) {

        lectureService.getLectureById(lectureId);

        Long attendeeId = attendeeService.getAttendeeIdByLogin(login);

        boolean isAttending = reservationRepository.findAllLectureIdsByAttendeeId(attendeeId).stream()
                .anyMatch(lecture -> lecture == lectureId);

        if (!isAttending) {
            throw new AttendeeIsNotAssignedToLectureException();
        }

        reservationRepository.deleteByLectureIdAndAttendeeId(lectureId, attendeeId);
    }

    private boolean isAttendeeBusy(String login, Lecture newLecture) {
        List<Lecture> userLectures = getUserLectures(login);

        return userLectures.stream()
                .anyMatch(userLecture ->
                        newLecture.getStartTime().isBefore(userLecture.getEndTime()) &&
                                newLecture.getEndTime().isAfter(userLecture.getStartTime())
                );
    }

    public int getAttendantsCount(Long lectureId) {
        return reservationRepository.countByLectureId(lectureId);
    }

    private void sendReservationSuccessfulEmail(Attendee attendee, Lecture lecture, Long reservationId) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        DateTimeFormatter emailFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String fileName = "reservation-" + reservationId + "-"
                + attendee.getEmail().substring(0, attendee.getEmail().indexOf('@')) + "-"
                + date.format(format) + ".txt";

        new File(fileName);

        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.println("Date: " + date.format(emailFormat));
            pw.println("Receiver: " + attendee.getEmail());
            pw.println("\nReservation details: ");
            pw.printf("\tLogin: %s\n\tLecture name: %s\n\tLecture start time: %s\n\tLecture end time: %s\n",
                    attendee.getLogin(),
                    lecture.getName(),
                    lecture.getStartTime().format(emailFormat),
                    lecture.getEndTime().format(emailFormat));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
