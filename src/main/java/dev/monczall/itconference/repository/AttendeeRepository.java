package dev.monczall.itconference.repository;

import dev.monczall.itconference.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    Optional<Attendee> findByLogin(String login);
}
