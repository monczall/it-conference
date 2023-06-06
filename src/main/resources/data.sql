INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_1_1', 'Path_1', 5, '2023-06-01T10:00:00.0', '2023-06-01T11:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_1_2', 'Path_2', 5, '2023-06-01T10:00:00.0', '2023-06-01T11:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_1_3', 'Path_3', 5, '2023-06-01T10:00:00.0', '2023-06-01T11:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_2_1', 'Path_1', 5, '2023-06-01T12:00:00.0', '2023-06-01T13:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_2_2', 'Path_2', 5, '2023-06-01T12:00:00.0', '2023-06-01T13:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_2_3', 'Path_3', 5, '2023-06-01T12:00:00.0', '2023-06-01T13:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_3_1', 'Path_1', 5, '2023-06-01T14:00:00.0', '2023-06-01T15:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_3_2', 'Path_2', 5, '2023-06-01T14:00:00.0', '2023-06-01T15:45:00.0');
INSERT INTO LECTURE(name, path_name, capacity, start_time, end_time) VALUES ('Lecture_3_3', 'Path_3', 5, '2023-06-01T14:00:00.0', '2023-06-01T15:45:00.0');

INSERT INTO ATTENDEE(login, email) VALUES ('test1', 'test1@xyz.com');
INSERT INTO ATTENDEE(login, email) VALUES ('test2', 'test2@xyz.com');
INSERT INTO ATTENDEE(login, email) VALUES ('test3', 'test3@xyz.com');

INSERT INTO RESERVATION(lecture_id, attendee_id) VALUES (1, 1);
INSERT INTO RESERVATION(lecture_id, attendee_id) VALUES (2, 2);
INSERT INTO RESERVATION(lecture_id, attendee_id) VALUES (3, 3);