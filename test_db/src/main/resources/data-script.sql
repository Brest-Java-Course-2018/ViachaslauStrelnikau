INSERT INTO groups (shortName, fullName,description)
VALUES ('A12', 'Architecture 12', 'Learn Architecture');
INSERT INTO groups (shortName, fullName,description)
VALUES ('A17', 'Architecture 17', 'Learn Architecture');
INSERT INTO groups (shortName, fullName,description)
VALUES ('E30', 'Electronics and programming', 'Learn programable microcontrollers');
INSERT INTO groups (shortName, fullName,description)
VALUES ('R15', 'Roads 5', 'Learn roads building');

INSERT INTO students (studentName, studentBirth,studentAvgMarks,groupId)
VALUES ('Ivan Ivanov', '1985-12-01',7.3,1);
INSERT INTO students (studentName, studentBirth,studentAvgMarks,groupId)
VALUES ('Petr Petrov', '1985-01-30',6.2,1);
INSERT INTO students (studentName, studentBirth,studentAvgMarks,groupId)
VALUES ('Sidor Sidorov', '1995-03-01',5.2,2);
INSERT INTO students (studentName, studentBirth,studentAvgMarks,groupId)
VALUES ('Semen Semenov', '1987-04-02',9.3,3);
INSERT INTO students (studentName, studentBirth,studentAvgMarks,groupId)
VALUES ('Kostia Kostin', '1983-07-22',7.3,3);

INSERT INTO users (userName, userPassword)
VALUES ('loiane', '$2a$10$2a4e8803c91cc5edca222evoNPfhdRyGEG9RZcg7.qGqTjuCgXKda');
INSERT INTO users (userName, userPassword)
VALUES ('Stepan', 'pass');
INSERT INTO users (userName, userPassword)
VALUES ('Fedor', 'pass');