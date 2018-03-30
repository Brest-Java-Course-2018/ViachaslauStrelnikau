DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
  groupId INT NOT NULL AUTO_INCREMENT,
  shortName VARCHAR(255) NOT NULL,
  fullName VARCHAR(255) NOT NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY (groupId)
);
DROP TABLE IF EXISTS students;
CREATE TABLE students (
  studentId INT NOT NULL AUTO_INCREMENT,
  studentName VARCHAR(255) NOT NULL,
  studentBirth DATE NOT NULL,
  studentAvgMarks  DOUBLE NOT NULL,
  groupId INT,
  PRIMARY KEY (studentId),
  FOREIGN KEY (groupId) REFERENCES groups(groupId)
);