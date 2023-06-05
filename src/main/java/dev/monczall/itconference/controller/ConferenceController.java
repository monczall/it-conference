package dev.monczall.itconference.controller;

import dev.monczall.itconference.controller.model.LectureDetailsDto;
import dev.monczall.itconference.model.Conference;
import dev.monczall.itconference.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conference")
public class ConferenceController {
    private final ConferenceService conferenceService;

    @GetMapping("")
    public Conference getConferenceInfo() {
        return conferenceService.getConferenceInfo();
    }

    @GetMapping("details/lectures")
    public List<LectureDetailsDto> getAllLecturesDetails() {
        return conferenceService.getAllLecturesDetails();
    }

}
