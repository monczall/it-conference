package dev.monczall.itconference.service;

import dev.monczall.itconference.model.Conference;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final Conference conference;

    private final LectureService lectureService;

    public Conference getConferenceInfo() {
        conference.setLectures(lectureService.getAllLectures());

        return conference;
    }
}
