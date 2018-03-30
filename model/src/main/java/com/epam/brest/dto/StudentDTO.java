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
    private String groupName;

    /**
     * StudentDTO class constructor with params
     *
     * @param studentId       Students ID
     * @param studentName     Full name of student
     * @param studentBirth    Date of birth of the student
     * @param studentAvgMarks Students average marks
     * @param groupName       group name
     */
    public StudentDTO(int studentId, String studentName, Date studentBirth, double studentAvgMarks, String groupName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentBirth = studentBirth;
        this.studentAvgMarks = studentAvgMarks;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentBirth(Date studentBirth) {
        this.studentBirth = studentBirth;
    }

    public void setStudentAvgMarks(double studentAvgMarks) {
        this.studentAvgMarks = studentAvgMarks;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentBirth=" + studentBirth +
                ", studentAvgMarks=" + studentAvgMarks +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return studentId == that.studentId &&
                Double.compare(that.studentAvgMarks, studentAvgMarks) == 0 &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(studentBirth, that.studentBirth) &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, studentName, studentBirth, studentAvgMarks, groupName);
    }
}
