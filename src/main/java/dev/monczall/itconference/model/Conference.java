package dev.monczall.itconference.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Component
@ToString
public class Conference {
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Lecture> lectures;

    public Conference() {
        this.date = LocalDate.of(2023, 6, 1);
        this.startTime = LocalDateTime.of(2023, 6, 1, 10, 0);
        this.endTime = LocalDateTime.of(2023, 6, 1, 15, 45);
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }
}
