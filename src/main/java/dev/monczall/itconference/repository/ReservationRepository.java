package dev.monczall.itconference.repository;

import dev.monczall.itconference.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r.lectureId FROM Reservation r WHERE r.attendeeId = :attendeeId")
    List<Long> findAllLectureIdsByAttendeeId(@Param("attendeeId") Long attendeeId);

    int countByLectureId(Long lectureId);

    @Transactional
    void deleteByLectureIdAndAttendeeId(Long lectureId, Long attendeeId);
}
