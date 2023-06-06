package dev.monczall.itconference.service;

import dev.monczall.itconference.controller.mapper.AttendeeDtoMapper;
import dev.monczall.itconference.controller.model.AttendeeDto;
import dev.monczall.itconference.exception.exceptions.AttendeeLoginAlreadyInUseException;
import dev.monczall.itconference.exception.exceptions.AttendeeNotFoundException;
import dev.monczall.itconference.exception.exceptions.EmailBadlyFormattedException;
import dev.monczall.itconference.model.Attendee;
import dev.monczall.itconference.repository.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public List<AttendeeDto> getAllAttendees() {
        return AttendeeDtoMapper.mapAttendeeToAttendeeDto(attendeeRepository.findAll());
    }

    public AttendeeDto updateAttendeeEmail(AttendeeDto attendeeDto) {
        Attendee attendeeToUpdate = getAttendeeByLogin(attendeeDto.login());

        if (!isEmailValid(attendeeDto.email())) {
            throw new EmailBadlyFormattedException();
        }

        attendeeToUpdate.setEmail(attendeeDto.email());

        attendeeRepository.save(attendeeToUpdate);

        return attendeeDto;
    }

    public Attendee registerNewAttendee(String login, String email) {
        if (attendeeRepository.findByLogin(login).isPresent()) {
            throw new AttendeeLoginAlreadyInUseException();
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

    public Attendee getAttendeeByLogin(String login) {
        return attendeeRepository.findByLogin(login).orElseThrow(
                () -> new AttendeeNotFoundException()
        );
    }

    public Long getAttendeeIdByLogin(String login) {
        Attendee attendee = attendeeRepository.findByLogin(login).orElseThrow(
                () -> new AttendeeNotFoundException()
        );

        return attendee.getId();
    }

    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public long countAllAttendees() {
        return attendeeRepository.count();
    }

    public void deleteAttendee(Attendee attendee) {
        attendeeRepository.delete(attendee);
    }

}
