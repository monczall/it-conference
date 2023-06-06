package dev.monczall.itconference.service;

import dev.monczall.itconference.controller.model.LectureDetailsDto;
import dev.monczall.itconference.controller.model.PathDetailsDto;
import dev.monczall.itconference.model.Conference;
import dev.monczall.itconference.model.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final Conference conference;

    private final LectureService lectureService;

    private final ReservationService reservationService;

    private final AttendeeService attendeeService;

    public Conference getConferenceInfo() {
        conference.setLectures(lectureService.getAllLectures());

        return conference;
    }

    public List<LectureDetailsDto> getAllLecturesDetails() {
        long attendeesCount = attendeeService.countAllAttendees();

        List<LectureDetailsDto> response = lectureService.getAllLectures().stream()
                .map(lecture -> {
                    int lectureLimit = lecture.getCapacity();
                    int lectureCount = reservationService.getAttendantsCount(lecture.getId());

                    return new LectureDetailsDto(
                            lecture.getName(),
                            lectureCount,
                            lectureLimit,
                            percentageString(lectureCount, lectureLimit),
                            percentageString(lectureCount, attendeesCount)
                    );
                })
                .sorted(Comparator.comparing(LectureDetailsDto::percentOfAllAttendees).reversed())
                .toList();

        return response;
    }

    public List<PathDetailsDto> getAllPathsDetails() {
        long attendeesCount = attendeeService.countAllAttendees();

        List<Lecture> allLectures = lectureService.getAllLectures();

        Map<String, Integer> pathAttendantsCount = new HashMap<>();

        for (Lecture lecture : allLectures) {
            String pathName = lecture.getPathName();
            int lectureCount = reservationService.getAttendantsCount(lecture.getId());

            pathAttendantsCount.put(pathName, pathAttendantsCount.getOrDefault(pathName, 0) + lectureCount);
        }

        List<PathDetailsDto> response = pathAttendantsCount.entrySet().stream()
                .map(entry ->
                        new PathDetailsDto(
                                entry.getKey(),
                                entry.getValue(),
                                percentageString(entry.getValue(), attendeesCount)
                        )
                )
                .sorted(Comparator.comparing(PathDetailsDto::percentOfAllAttendees).reversed())
                .toList();

        return response;
    }

    private String percentageString(Integer nominator, long denominator) {
        float result =  (float) nominator / denominator * 100;

        if (result % 1 == 0) {
           return String.format("%.0f%%", result);
        } else {
           return String.format("%.1f%%", result);
        }
    }
}
