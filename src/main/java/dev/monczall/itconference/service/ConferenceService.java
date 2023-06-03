package dev.monczall.itconference.service;

import dev.monczall.itconference.model.Conference;
import dev.monczall.itconference.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final Conference conference;

    private final LectureRepository lectureRepository;

    public Conference getConferenceInfo() {
        conference.setLectures(lectureRepository.findAll());

        return conference;
    }
}
