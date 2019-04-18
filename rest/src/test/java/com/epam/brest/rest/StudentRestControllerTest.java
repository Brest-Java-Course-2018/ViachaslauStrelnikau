package com.epam.brest.rest;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import com.epam.brest.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class StudentRestControllerTest tests StudentRestController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-test.xml", "classpath:test-db-spring.xml", "classpath:dao.xml"})
@Rollback
public class StudentRestControllerTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRestController studentRestController;

    private Student student;

    private MockMvc mockMvc;

    /**
     * Test set up method.
     */
    @Before
    public void testSetUp() throws ParseException {
        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();

        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        student = new Student();
        student.setGroupId(1);
        date = simpleDateFormat.parse("31.05.1990");
        student.setStudentBirth(new java.sql.Date(date.getTime()));
        student.setStudentAvgMarks(1);
        student.setStudentName("Student1");
    }

    /**
     * Test get mapping of request "/students".
     */
    @Test
    public void getAllStudentsRestTest() throws Exception {
        Collection<StudentDTO> studentDTOS = studentService.getallStudentsDTO();
        StudentDTO studentDTO = studentDTOS.iterator().next();
        mockMvc.perform(get("/students")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].studentId", Matchers.is(studentDTO.getStudentId())))
                .andExpect(jsonPath("$[0].studentName", Matchers.is(studentDTO.getStudentName())))
                .andExpect(jsonPath("$[0].studentBirth", Matchers.is(studentDTO.getStudentBirth().getTime())))
                .andExpect(jsonPath("$[0].studentAvgMarks", Matchers.is(studentDTO.getStudentAvgMarks())))
                .andExpect(jsonPath("$[0].fullName", Matchers.is(studentDTO.getFullName())));
    }

    /**
     * Test get mapping of request "/students/{dateFrom}/{dateTo}".
     */
    @Test
    public void getFilteredStudentsDTORestTest() throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = simpleDateFormat.parse("31.05.1990");
        Date dateTo = simpleDateFormat.parse("31.05.2100");
        java.sql.Date dateFromSql = new java.sql.Date(dateFrom.getTime());
        java.sql.Date dateToSql = new java.sql.Date(dateTo.getTime());

        Collection<StudentDTO> studentDTOS = studentService.getFilteredStudentsDTO(dateFromSql, dateToSql);
        StudentDTO studentDTO = studentDTOS.iterator().next();
        mockMvc.perform(get("/students/{dateFrom}/{dateTo}", dateFromSql.getTime(), dateToSql.getTime())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].studentId", Matchers.is(studentDTO.getStudentId())))
                .andExpect(jsonPath("$[0].studentName", Matchers.is(studentDTO.getStudentName())))
                .andExpect(jsonPath("$[0].studentBirth", Matchers.is(studentDTO.getStudentBirth().getTime())))
                .andExpect(jsonPath("$[0].studentAvgMarks", Matchers.is(studentDTO.getStudentAvgMarks())))
                .andExpect(jsonPath("$[0].fullName", Matchers.is(studentDTO.getFullName())));

    }

    /**
     * Test get mapping of request "/students/{id}".
     */
    @Test
    public void getStudentByIdRestTest() throws Exception {
        Student student1 = studentService.addStudent(student);
        mockMvc.perform(get("/students/{id}", student1.getStudentId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("studentId", Matchers.is(student1.getStudentId())))
                .andExpect(jsonPath("studentName", Matchers.is(student1.getStudentName())))
             //   .andExpect(jsonPath("studentBirth", Matchers.is(student1.getStudentBirth().toString())))
                .andExpect(jsonPath("studentAvgMarks", Matchers.is(student1.getStudentAvgMarks())))
                .andExpect(jsonPath("groupId", Matchers.is(student1.getGroupId())));

    }

    /**
     * Test post mapping of request "/students".
     */
    @Test
    public void addStudentRestTest() throws Exception {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();

        mockMvc.perform(
                post("/students")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(student))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("studentName", Matchers.is(student.getStudentName())))
                .andExpect(jsonPath("studentBirth", Matchers.is(student.getStudentBirth().toString())))
                .andExpect(jsonPath("studentAvgMarks", Matchers.is(student.getStudentAvgMarks())))
                .andExpect(jsonPath("groupId", Matchers.is(student.getGroupId())));

    }

    /**
     * Test post mapping of request "/students/{id}".
     */
    @Test
    public void updateStudentTest() throws Exception {
        Student student1 = studentService.addStudent(student);

        student1.setStudentName("NewName");
        student1.setStudentAvgMarks(0);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();

        mockMvc.perform(
                post("/students/{id}", student1.getStudentId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(student1))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isOk());

        Student student2 = studentService.getStudentById(student1.getStudentId());
        Assert.assertTrue(student1.getStudentName().equals(student2.getStudentName()));
        Assert.assertTrue(student1.getStudentAvgMarks() == student2.getStudentAvgMarks());
    }

    /**
     * Test delete mapping of request "/students/{id}".
     */
    @Test
    public void removeStudent() throws Exception {
        int size_befor = studentService.getallStudentsDTO().size();
        mockMvc.perform(
                delete("/students/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
        int size_after = studentService.getallStudentsDTO().size();
        Assert.assertTrue(size_after + 1 == size_befor);

    }
}