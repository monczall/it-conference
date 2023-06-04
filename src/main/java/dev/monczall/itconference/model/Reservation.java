package dev.monczall.itconference.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lecture_id")
    private long lectureId;

    @Column(name = "attendee_id")
    private long attendeeId;
}
