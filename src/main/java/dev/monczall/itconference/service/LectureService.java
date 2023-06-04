package dev.monczall.itconference.service;

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

    public String getLectureNameById(Long id) {
        return lectureRepository.findNameById(id);
    }

    public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public List<Lecture> getAllLecturesByIds(List<Long> lectureIds) {
        return lectureRepository.findAllById(lectureIds);
    }
}
