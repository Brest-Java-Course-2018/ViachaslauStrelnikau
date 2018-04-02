package com.epam.brest.service;

import com.epam.brest.dao.StudentDao;
import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class StudentServiceImplTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentDao mockStudentDao;

    private StudentDTO studentDTO;
    private StudentDTO studentDTO2;
    private Student student;
    private Student student_in;

    /**
     * Test set up.
     */
    @Before
    public void setUpTest() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        studentDTO = new StudentDTO();
        studentDTO.setStudentId(1);
        studentDTO.setFullName("Test1");
        Date date = simpleDateFormat.parse("31.05.1990");
        studentDTO.setStudentBirth(new java.sql.Date(date.getTime()));
        studentDTO.setStudentAvgMarks(1);
        studentDTO.setStudentName("Student1");

        studentDTO2 = new StudentDTO();
        studentDTO2.setStudentId(2);
        studentDTO2.setFullName("Test2");
        date = simpleDateFormat.parse("31.05.1990");
        studentDTO2.setStudentBirth(new java.sql.Date(date.getTime()));
        studentDTO2.setStudentAvgMarks(2);
        studentDTO2.setStudentName("Student2");

        student = new Student();
        student.setStudentId(2);
        student.setGroupId(2);
        date = simpleDateFormat.parse("31.05.1990");
        student.setStudentBirth(new java.sql.Date(date.getTime()));
        student.setStudentAvgMarks(2);
        student.setStudentName("Student2");

        student_in = new Student();
        student_in.setGroupId(2);
        date = simpleDateFormat.parse("31.05.1990");
        student_in.setStudentBirth(new java.sql.Date(date.getTime()));
        student_in.setStudentAvgMarks(2);
        student_in.setStudentName("Student2");
    }

    /**
     * Test clean.
     */
    @After
    public void TestClear() {
        EasyMock.verify(mockStudentDao);
        EasyMock.reset(mockStudentDao);
    }

    /**
     * getallStudentsDTO method test.
     */
    @Test
    public void getallStudentsDTO() {
        EasyMock.expect(mockStudentDao.getallStudentsDTO()).andReturn(Arrays.asList(studentDTO, studentDTO2));
        EasyMock.replay(mockStudentDao);
        Collection<StudentDTO> studentDTOS = studentService.getallStudentsDTO();
    }

    /**
     * getFilteredStudentsDTO method test.
     */
    @Test
    public void getFilteredStudentsDTO() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = simpleDateFormat.parse("31.05.1890");
        Date dateTo = simpleDateFormat.parse("31.05.3990");
        java.sql.Date dateFromSQL = new java.sql.Date(dateFrom.getTime());
        java.sql.Date dateToSQL = new java.sql.Date(dateTo.getTime());
        EasyMock.expect(mockStudentDao.getFilteredStudentsDTO(dateFromSQL, dateToSQL)).andReturn(Arrays.asList(studentDTO, studentDTO2));
        EasyMock.replay(mockStudentDao);

        studentService.getFilteredStudentsDTO(dateFromSQL, dateToSQL);
    }

    /**
     * getStudentById method test.
     */
    @Test
    public void getStudentById() {
        EasyMock.expect(mockStudentDao.getStudentById(2)).andReturn(student);
        EasyMock.replay(mockStudentDao);

        studentService.getStudentById(2);
    }

    /**
     * addStudent method test.
     */
    @Test
    public void addStudent() {
        EasyMock.expect(mockStudentDao.addStudent(student_in)).andReturn(student);
        EasyMock.replay(mockStudentDao);

        studentService.addStudent(student_in);

    }

    /**
     * updateStudent method test.
     */
    @Test
    public void updateStudent() {
        mockStudentDao.updateStudent(student);
        EasyMock.replay(mockStudentDao);

        studentService.updateStudent(student);
    }

    /**
     * removeStudent method test.
     */
    @Test
    public void removeStudent() {
        mockStudentDao.removeStudent(1);
        EasyMock.replay(mockStudentDao);

        studentService.removeStudent(1);
    }
}