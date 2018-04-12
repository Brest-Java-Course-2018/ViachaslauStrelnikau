package com.epam.brest.rest_client;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import com.epam.brest.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Collection;

/**
 * Class StudentCounsumerRest implements StudentService to fully compatible with rest service.
 */
public class StudentCounsumerRest implements StudentService {
    private static final Logger LOGGER = LogManager.getLogger();

    private RestTemplate restTemplate;
    private String url;

    /**
     * StudentCounsumerRest constructor.
     * @param url request url
     * @param restTemplate  rest Template
     */
    public StudentCounsumerRest(String url,RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    /**
     * getallStudentsDTO method gets collection of StudentDTO through rest service.
     * @return Collection of StudentDTO records
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<StudentDTO> getallStudentsDTO() {

        LOGGER.debug("StudentCounsumerRest getallStudentsDTO");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, Collection.class);
        Collection<StudentDTO> groupDTOs =
                (Collection<StudentDTO>) responseEntity.getBody();
        return groupDTOs;
    }

    /**
     * getFilteredStudentsDTO method gets collection of StudentDTO,
     * with date of birth in interval dateFrom to dateTo, through rest service.
     * @param dateFrom begin date interval
     * @param dateTo   end date interval
     * @return Collection of StudentDTO records
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<StudentDTO> getFilteredStudentsDTO(Date dateFrom, Date dateTo)  {
        LOGGER.debug("StudentCounsumerRest getFilteredStudentsDTO from:{},to:{}", dateFrom, dateTo);
       // Date dateFromSql = new Date(dateFrom.getTime());
   //     Date dateToSql = new Date(dateTo.getTime());
        String dateFromStr="";
        String dateToStr="";
        if(dateFrom==null)dateFromStr="/0";
        else
            dateFromStr="/"+dateFrom.getTime();
        if(dateTo==null)dateToStr="/0";
        else
            dateToStr="/"+dateTo.getTime();

        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + dateFromStr+ dateToStr, Collection.class);
        Collection<StudentDTO> groupDTOs =
                (Collection<StudentDTO>) responseEntity.getBody();
        return groupDTOs;
    }

    /**
     * getStudentById method get Student record by its id, through rest service.
     * @param id id of student
     * @return Student record
     */
    @Override
    public Student getStudentById(int id) {
        LOGGER.debug("StudentCounsumerRest getStudentById {}", id);
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + id, Student.class);
        Student student = (Student) responseEntity.getBody();
        return student;
    }

    /**
     * addStudent method add Student record to database, through rest service.
     * @param student student
     * @return Student record that was added to database
     */
    @Override
    public Student addStudent(Student student) {
        LOGGER.debug("StudentCounsumerRest addStudent - {}", student);
        ResponseEntity responseEntity =
                restTemplate.postForEntity(url, student, Student.class);
        Student student1 = (Student) responseEntity.getBody();
        return student1;
    }

    /**
     * updateStudent method update Student record in the database, through rest service.
     * @param student student
     */
    @Override
    public void updateStudent(Student student) {
        LOGGER.debug("StudentCounsumerRest updateStudent - {}", student);
        restTemplate
                .postForEntity(url + "/" + student.getStudentId(), student, Student.class);
    }

    /**
     * removeStudent method remove Student record from the database by its id, through rest service.
     * @param id id of record to remove
     */
    @Override
    public void removeStudent(int id) {
        LOGGER.debug("StudentCounsumerRest removeStudent - {}", id);
        restTemplate.delete(url + "/" + id);

    }
}
