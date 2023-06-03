package dev.monczall.itconference.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class Lecture {
    private Long id;
    private String name;
    private String pathName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
