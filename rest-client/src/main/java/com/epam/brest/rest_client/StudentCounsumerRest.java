package com.epam.brest.rest_client;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import com.epam.brest.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

public class StudentCounsumerRest implements StudentService {
    private static final Logger LOGGER = LogManager.getLogger();

    private RestTemplate restTemplate;
    private String url;

    public StudentCounsumerRest(String url,RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public Collection<StudentDTO> getallStudentsDTO() {

        LOGGER.debug("StudentCounsumerRest getallStudentsDTO");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, Collection.class);
        Collection<StudentDTO> groupDTOs =
                (Collection<StudentDTO>) responseEntity.getBody();
        return groupDTOs;
    }

    @Override
    public Collection<StudentDTO> getFilteredStudentsDTO(Date dateFrom, Date dateTo) throws ParseException {
        LOGGER.debug("StudentCounsumerRest getFilteredStudentsDTO from:{},to:{}", dateFrom, dateTo);
        Date dateFromSql = new Date(dateFrom.getTime());
        Date dateToSql = new Date(dateTo.getTime());
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + dateFromSql + "/" + dateToSql, Collection.class);
        Collection<StudentDTO> studentDTOS =
                (Collection<StudentDTO>) responseEntity.getBody();
        return studentDTOS;
    }

    @Override
    public Student getStudentById(int id) {
        LOGGER.debug("StudentCounsumerRest getStudentById {}", id);
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + id, Student.class);
        Student student = (Student) responseEntity.getBody();
        return student;
    }

    @Override
    public Student addStudent(Student student) {
        LOGGER.debug("StudentCounsumerRest addStudent - {}", student);
        ResponseEntity responseEntity =
                restTemplate.postForEntity(url, student, Student.class);
        Student student1 = (Student) responseEntity.getBody();
        return student1;
    }

    @Override
    public void updateStudent(Student student) {
        LOGGER.debug("StudentCounsumerRest updateStudent - {}", student);
        restTemplate.postForEntity(url + "/" + student.getStudentId(), student, Student.class);
    }

    @Override
    public void removeStudent(int id) {
        LOGGER.debug("StudentCounsumerRest removeStudent - {}", id);
        restTemplate.delete(url + "/" + id);

    }
}
