package com.epam.brest.rest_client;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Class StudentCounsumerRestTest tests StudentCounsumerRest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-restclient-context.xml"})
public class StudentCounsumerRestTest {

    @Autowired
    RestTemplate restTemplateMock;

    @Autowired
    StudentCounsumerRest studentservice;

    private static int ID=2;
    private static Student student;
    private StudentDTO studentDTO;
    private StudentDTO studentDTO2;
    /**
     * testSetUp- test setUp method.
     */
    @Before
    public void testSetUp() throws ParseException {
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
    }
    /**
     * testEnd  test end method.
     */
    @After
    public void testEnd()
    {
        verify(restTemplateMock);
        reset(restTemplateMock);
    }

    /**
     * getallStudentsDTO method mock test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getallStudentsDTO() {
        Collection<StudentDTO> studentDTOS = Arrays.asList(studentDTO,studentDTO2);
        ResponseEntity responseEntity = new ResponseEntity(studentDTOS, HttpStatus.OK);

        expect(restTemplateMock.getForEntity(anyString(),anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);
        Collection<StudentDTO> studentDTOS1 = studentservice.getallStudentsDTO();
        Assert.assertNotNull(studentDTOS1);
        Assert.assertEquals(studentDTOS1.size(),2);
    }
    /**
     * getFilteredStudentsDTO method mock test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getFilteredStudentsDTO() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = simpleDateFormat.parse("31.05.1990");
        java.sql.Date dateFrom=new java.sql.Date(date.getTime());
        date = simpleDateFormat.parse("31.05.2590");
        java.sql.Date dateTo=new java.sql.Date(date.getTime());
        Collection<StudentDTO> studentDTOS = Arrays.asList(studentDTO,studentDTO2);
        ResponseEntity responseEntity = new ResponseEntity(studentDTOS,HttpStatus.OK);
        expect(restTemplateMock.getForEntity(anyString(),anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);
        Collection<StudentDTO> studentDTOS1 = studentservice.getFilteredStudentsDTO(dateFrom,dateTo);

        Assert.assertNotNull(studentDTOS1);
        Assert.assertEquals(studentDTOS1.size(),2);

    }
    /**
     * getStudentById method mock test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getStudentById() {
        ResponseEntity responseEntity = new ResponseEntity(student, HttpStatus.FOUND);
        expect(restTemplateMock.getForEntity(anyString(),anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);

        Student student1 = studentservice.getStudentById(ID);
        Assert.assertEquals(student1,student);
    }
    /**
     * addStudent method mock test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void addStudent() {
        ResponseEntity responseEntity = new ResponseEntity(student,HttpStatus.CREATED);
        expect(restTemplateMock.postForEntity(anyString(),anyObject(),anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);
        Student student1 = studentservice.addStudent(student);
        Assert.assertEquals(student1,student);
    }
    /**
     * updateStudent method mock test.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void updateStudent() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        expect(restTemplateMock.postForEntity(anyString(),anyObject(),anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);
        studentservice.updateStudent(student);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    /**
     * removeStudent method mock test.
     */
    @Test
    public void removeStudent() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.FOUND);
        restTemplateMock.delete(anyString());
        replay(restTemplateMock);

        studentservice.removeStudent(ID);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
    }
}