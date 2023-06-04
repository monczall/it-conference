package dev.monczall.itconference.repository;

import dev.monczall.itconference.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query("SELECT l.name FROM Lecture l WHERE l.id = :id")
    String findNameById(Long id);
}
