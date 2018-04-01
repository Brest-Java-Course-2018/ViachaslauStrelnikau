package com.epam.brest.service;

import com.epam.brest.dao.StudentDao;
import com.epam.brest.dto.StudentDTO;
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
    }
    @After
    public void TestClear()
    {
        EasyMock.verify(mockStudentDao);
        EasyMock.reset(mockStudentDao);
    }

    @Test
    public void getallStudentsDTO() {
        EasyMock.expect(mockStudentDao.getallStudentsDTO()).andReturn(Arrays.asList(studentDTO,studentDTO2));
        EasyMock.replay(mockStudentDao);
        Collection<StudentDTO> studentDTOS= studentService.getallStudentsDTO();
    }

//    @Test
//    public void getFilteredStudentsDTO() throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        Date dateFrom = simpleDateFormat.parse("31.05.1890");
//        Date dateTo = simpleDateFormat.parse("31.05.3990");
//
//    }

//    @Test
//    public void getStudentById() {
//    }
//
//    @Test
//    public void addStudent() {
//    }
//
//    @Test
//    public void updateStudent() {
//    }
//
//    @Test
//    public void removeStudent() {
//    }
}