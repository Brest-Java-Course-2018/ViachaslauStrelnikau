package com.epam.brest.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

/**
 * Student class
 */
public class Student {
    /**
     * Property studentId.
     */
    private int studentId;
    /**
     * Property studentName.
     */
    @Size(min = 2, max = 50)
    private String studentName;
    /**
     * Property studentBirth students date of birth.
     */
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Minsk")
    private Date studentBirth;
    /**
     * Property studentAvgMarks.
     */
    @DecimalMin("1")
    @DecimalMax("10")
    private double studentAvgMarks;
    /**
     * Property groupId.
     */
    private int groupId;

    /**
     * Student class constructor with params
     *
     * @param studentName     Full name of student
     * @param studentBirth    Date of birth of the student
     * @param studentAvgMarks Students average marks
     * @param groupId         ID of students group
     */
    public Student(final String studentName, final Date studentBirth, final double studentAvgMarks, final int groupId) {
        this.studentName = studentName;
        this.studentBirth = studentBirth;
        this.studentAvgMarks = studentAvgMarks;
        this.groupId = groupId;
    }

    /**
     * Student class constructor without params
     */
    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Date getStudentBirth() {
        return studentBirth;
    }

    public double getStudentAvgMarks() {
        return studentAvgMarks;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setStudentId(final int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(final String studentName) {
        this.studentName = studentName;
    }

    public void setStudentBirth(final Date studentBirth) {
        this.studentBirth = studentBirth;
    }

    public void setStudentAvgMarks(final double studentAvgMarks) {
        this.studentAvgMarks = studentAvgMarks;
    }

    public void setGroupId(final int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student{"
                + "studentId=" + studentId
                + ", studentName='" + studentName + '\''
                + ", studentBirth=" + studentBirth
                + ", studentAvgMarks=" + studentAvgMarks
                + ", groupId=" + groupId
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.studentAvgMarks, studentAvgMarks) == 0
                && Objects.equals(studentId, student.studentId)
                && Objects.equals(studentName, student.studentName)
                && Objects.equals(studentBirth, student.studentBirth)
                && Objects.equals(groupId, student.groupId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentName, studentBirth, studentAvgMarks, groupId);
    }
}
