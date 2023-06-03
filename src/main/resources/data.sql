INSERT INTO ATTENDEE(id, login, email) VALUES (1, 'test1', 'test1@xyz.com');
INSERT INTO ATTENDEE(id, login, email) VALUES (2, 'test2', 'test2@xyz.com');
INSERT INTO ATTENDEE(id, login, email) VALUES (3, 'test3', 'test3@xyz.com');

INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (1, 'Lecture_1_1', 'Path_1', '2023-06-01T10:00:00.0', '2023-06-01T11:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (2, 'Lecture_1_2', 'Path_2', '2023-06-01T10:00:00.0', '2023-06-01T11:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (3, 'Lecture_1_3', 'Path_3', '2023-06-01T10:00:00.0', '2023-06-01T11:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (4, 'Lecture_2_1', 'Path_1', '2023-06-01T12:00:00.0', '2023-06-01T13:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (5, 'Lecture_2_2', 'Path_2', '2023-06-01T12:00:00.0', '2023-06-01T13:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (6, 'Lecture_2_3', 'Path_3', '2023-06-01T12:00:00.0', '2023-06-01T13:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (7, 'Lecture_3_1', 'Path_1', '2023-06-01T14:00:00.0', '2023-06-01T15:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (8, 'Lecture_3_2', 'Path_2', '2023-06-01T14:00:00.0', '2023-06-01T15:45:00.0');
INSERT INTO LECTURE(id, name, path_name, start_time, end_time) VALUES (9, 'Lecture_3_3', 'Path_3', '2023-06-01T14:00:00.0', '2023-06-01T15:45:00.0');

INSERT INTO RESERVATION(id, lecture_id, attendee_id) VALUES (1, 1, 1);
INSERT INTO RESERVATION(id, lecture_id, attendee_id) VALUES (2, 1, 2);
INSERT INTO RESERVATION(id, lecture_id, attendee_id) VALUES (3, 2, 1);