INSERT INTO ATTENDEE(id, login, email) VALUES (1, 'test1', 'test1@xyz.com');
INSERT INTO ATTENDEE(id, login, email) VALUES (2, 'test2', 'test2@xyz.com');
INSERT INTO ATTENDEE(id, login, email) VALUES (3, 'test3', 'test3@xyz.com');

INSERT INTO RESERVATION(id, lecture_id, attendee_id) VALUES (1, 1, 1);
INSERT INTO RESERVATION(id, lecture_id, attendee_id) VALUES (2, 1, 2);
INSERT INTO RESERVATION(id, lecture_id, attendee_id) VALUES (3, 2, 1);