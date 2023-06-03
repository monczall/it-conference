package dev.monczall.itconference.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Component
public class Conference {
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Lecture> lectures;

    public Conference() {

        LocalDateTime firstLectureStartTime = LocalDateTime.of(2023, 6, 1, 10, 0);
        LocalDateTime secondLectureStartTime = LocalDateTime.of(2023, 6, 1, 12, 0);
        LocalDateTime thirdLectureStartTime = LocalDateTime.of(2023, 6, 1, 14, 0);
        LocalDateTime firstLectureEndTime = LocalDateTime.of(2023, 6, 1, 11, 45);
        LocalDateTime secondLectureEndTime = LocalDateTime.of(2023, 6, 1, 13, 45);
        LocalDateTime thirdLectureEndTime = LocalDateTime.of(2023, 6, 1, 15, 45);

        List<Lecture> lectures = Stream.of(
                new Lecture(1L, "Lecture_1_1", "Path_1", firstLectureStartTime, firstLectureEndTime),
                new Lecture(2L, "Lecture_1_2", "Path_2", firstLectureStartTime, firstLectureEndTime),
                new Lecture(3L, "Lecture_1_3", "Path_3", firstLectureStartTime, firstLectureEndTime),
                new Lecture(4L, "Lecture_2_1", "Path_1", secondLectureStartTime, secondLectureEndTime),
                new Lecture(5L, "Lecture_2_2", "Path_2", secondLectureStartTime, secondLectureEndTime),
                new Lecture(6L, "Lecture_2_3", "Path_3", secondLectureStartTime, secondLectureEndTime),
                new Lecture(7L, "Lecture_3_1", "Path_1", thirdLectureStartTime, thirdLectureEndTime),
                new Lecture(8L, "Lecture_3_2", "Path_2", thirdLectureStartTime, thirdLectureEndTime),
                new Lecture(9L, "Lecture_3_3", "Path_3", thirdLectureStartTime, thirdLectureEndTime)
        ).toList();


        this.date = LocalDate.of(2023, 6, 1);
        this.startTime = LocalDateTime.of(2023, 6, 1, 10, 0);
        this.endTime = LocalDateTime.of(2023, 6, 1, 15, 45);
        this.lectures = lectures;
    }
}
