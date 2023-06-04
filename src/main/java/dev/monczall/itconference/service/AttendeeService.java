package dev.monczall.itconference.service;

import dev.monczall.itconference.exception.exceptions.AttendeeLoginAlreadyInUseException;
import dev.monczall.itconference.exception.exceptions.AttendeeNotFoundException;
import dev.monczall.itconference.model.Attendee;
import dev.monczall.itconference.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public Attendee registerNewAttendee(String login, String email) {
        if(attendeeRepository.findByLogin(login).isPresent()) {
            throw new AttendeeLoginAlreadyInUseException("Attendee with given login already exists");
        }

        Attendee attendee = new Attendee();
        attendee.setLogin(login);
        attendee.setEmail(email);

        return attendeeRepository.save(attendee);
    }

    public boolean checkAttendeeExistsByLogin(String login) {
        return attendeeRepository.findByLogin(login).isPresent();
    }

    public boolean checkAttendeeLoginAndEmailMatch(String login, String email) {
        Optional<Attendee> optionalAttendee = attendeeRepository.findByLogin(login);

        return optionalAttendee.map(attendee -> attendee.getEmail().equals(email)).orElse(true);

    }

    public boolean checkAttendeeExistsWithLoginAndEmailOrUnique(String login, String email) {
        Optional<Attendee> optionalAttendee = attendeeRepository.findByLogin(login);

        return optionalAttendee.map(attendee -> attendee.getEmail().equals(email))
                .orElse(true);
    }

    public Attendee getAttendeeByLogin(String login) {
        return attendeeRepository.findByLogin(login).orElseThrow(
                () -> new AttendeeNotFoundException("Attendee not found")
        );
    }

    public Long getAttendeeIdByLogin(String login) {
        Attendee attendee = attendeeRepository.findByLogin(login).orElseThrow(
                () -> new AttendeeNotFoundException("Attendee not found")
        );

        return attendee.getId();
    }


}
