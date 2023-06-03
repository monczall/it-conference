DROP TABLE IF EXISTS ATTENDEE;
DROP TABLE IF EXISTS RESERVATION;
DROP TABLE IF EXISTS LECTURE;

CREATE TABLE ATTENDEE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE LECTURE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    path_name VARCHAR(255) NOT NULL,
    start_time timestamp NOT NULL,
    end_time timestamp NOT NULL
);

CREATE TABLE RESERVATION (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecture_id BIGINT NOT NULL,
    attendee_id BIGINT NOT NULL
);

ALTER TABLE RESERVATION
    ADD CONSTRAINT reservation_attendee_id
    FOREIGN KEY (attendee_id) REFERENCES ATTENDEE(id);

ALTER TABLE RESERVATION
    ADD CONSTRAINT reservation_lecture_id
    FOREIGN KEY (lecture_id) REFERENCES LECTURE(id)