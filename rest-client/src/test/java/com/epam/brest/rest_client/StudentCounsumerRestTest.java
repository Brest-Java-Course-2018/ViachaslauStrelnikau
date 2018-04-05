package com.epam.brest.rest_client;

import com.epam.brest.dto.StudentDTO;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-restclient-context.xml"})
public class StudentCounsumerRestTest {

    @Autowired
    RestTemplate restTemplateMock;

    @Autowired
    StudentCounsumerRest studentservice;

    private StudentDTO studentDTO;
    private StudentDTO studentDTO2;

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
    }

    @After
    public void testEnd()
    {
        verify(restTemplateMock);
        reset(restTemplateMock);
    }

    @Test
    public void getallStudentsDTO() {
        Collection<StudentDTO> studentDTOS = Arrays.asList(studentDTO,studentDTO2);
        ResponseEntity responseEntity = new ResponseEntity(studentDTOS, HttpStatus.OK);

        expect(restTemplateMock.getForEntity(anyString(),anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);
        Collection<StudentDTO> studentDTOS1 = studentservice.getallStudentsDTO();
        Assert.assertNotNull(studentDTOS1);
        Assert.assertEquals(studentDTOS1.size(),2);
    }

    @Test
    public void getFilteredStudentsDTO() {
    }

    @Test
    public void getStudentById() {
    }

    @Test
    public void addStudent() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void removeStudent() {
    }
}