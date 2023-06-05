package dev.monczall.itconference.service;

import dev.monczall.itconference.controller.model.LectureDetailsDto;
import dev.monczall.itconference.model.Conference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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
                            (lectureCount * 100) / lectureLimit,
                            (lectureCount * 100) / attendeesCount
                    );
                })
                .sorted(Comparator.comparingDouble(LectureDetailsDto::percentOfAllAttendees).reversed())
                .toList();

        return response;
    }
}
