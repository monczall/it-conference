package dev.monczall.itconference.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Attendee {
    @Id
    private Long id;
    private String login;
    private String email;
}
