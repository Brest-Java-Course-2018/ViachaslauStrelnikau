package com.epam.brest.dto;

import java.sql.Date;
import java.util.Objects;

/**
 * StudentDTO class
 */
public class StudentDTO {
    /**
     * Property studentId.
     */
    private int studentId;
    /**
     * Property studentName.
     */
    private String studentName;
    /**
     * Property studentBirth students date of birth.
     */
    private Date studentBirth;
    /**
     * Property studentAvgMarks.
     */
    private double studentAvgMarks;
    /**
     * Property groupName.
     */
    private String fullName;

    /**
     * StudentDTO class constructor with params
     *
     * @param studentId       Students ID
     * @param studentName     Full name of student
     * @param studentBirth    Date of birth of the student
     * @param studentAvgMarks Students average marks
     * @param fullName       group name
     */
    public StudentDTO(final int studentId, final String studentName, final Date studentBirth,
                      final double studentAvgMarks, final String fullName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentBirth = studentBirth;
        this.studentAvgMarks = studentAvgMarks;
        this.fullName = fullName;
    }

    /**
     * StudentDTO class constructor without params
     */
    public StudentDTO() {
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

    public String getFullName() {
        return fullName;
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

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "StudentDTO{"
                + "studentId=" + studentId
                + ", studentName='" + studentName + '\''
                + ", studentBirth=" + studentBirth
                + ", studentAvgMarks=" + studentAvgMarks
                + ", groupName='" + fullName + '\''
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return studentId == that.studentId
                && Double.compare(that.studentAvgMarks, studentAvgMarks) == 0
                && Objects.equals(studentName, that.studentName)
                && Objects.equals(studentBirth, that.studentBirth)
                && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentName, studentBirth, studentAvgMarks, fullName);
    }
}
