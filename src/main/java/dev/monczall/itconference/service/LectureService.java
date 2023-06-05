package dev.monczall.itconference.service;

import dev.monczall.itconference.exception.exceptions.LectureNotFoundException;
import dev.monczall.itconference.model.Lecture;
import dev.monczall.itconference.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture getLectureById(Long lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> new LectureNotFoundException());
    }

    public List<Lecture> getAllLecturesByIds(List<Long> lectureIds) {
        return lectureRepository.findAllById(lectureIds);
    }

}
