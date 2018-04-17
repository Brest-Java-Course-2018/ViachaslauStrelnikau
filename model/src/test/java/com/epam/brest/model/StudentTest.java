package com.epam.brest.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
/**
 * Test Student class.
 */
public class StudentTest {

    private static Student student;
    private static Student student1;

    private static final int ID=1;
    private static final String NAME="Ivanov";
    private static final double AVGMARKS=3.3;
    private static final int GROUP_ID=1;
    private static  Date DATE;
    private static String studentToString;
    /**
     * Test set up.
     */
    @Before
    public void setUpTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DATE= new Date(simpleDateFormat.parse("31.05.1990").getTime());
        studentToString="Student{"
                + "studentId=" + ID
                + ", studentName='" + NAME + '\''
                + ", studentBirth=" + DATE
                + ", studentAvgMarks=" + AVGMARKS
                + ", groupId=" + GROUP_ID
                + '}';
    }
    /**
     * Method studentTest tests all methods of Student class.
     */
    @Test
    public void studentTest()
    {
        student1 = new Student(NAME,DATE,AVGMARKS,GROUP_ID);
        student1.setStudentId(ID);
        student= new Student();
        student.setStudentId(ID);
        student.setStudentBirth(DATE);
        student.setStudentAvgMarks(AVGMARKS);
        student.setStudentName(NAME);
        student.setGroupId(GROUP_ID);

        Assert.assertEquals(student.getStudentId(),ID);
        Assert.assertEquals(student.getStudentName(),NAME);
        Assert.assertEquals(student.getStudentBirth(),DATE);
        Assert.assertTrue(student.getStudentAvgMarks()==AVGMARKS);
        Assert.assertEquals(student.getGroupId(),GROUP_ID);

        Assert.assertEquals(student,student1);

        Assert.assertEquals(student.toString(),studentToString);

        Assert.assertEquals(student.hashCode(), Objects.hash(ID,NAME,DATE,AVGMARKS,GROUP_ID));

    }

}