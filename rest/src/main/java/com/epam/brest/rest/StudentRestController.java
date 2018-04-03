package com.epam.brest.rest;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import com.epam.brest.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

@RestController
public class StudentRestController {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students")
    @ResponseStatus(HttpStatus.OK)
    Collection<StudentDTO> getAllStudentsRest() {
        LOGGER.debug("StudentRestController getAllStudentsRest");
        Collection<StudentDTO> studentDTOS = studentService.getallStudentsDTO();
        return studentDTOS;
    }

    @GetMapping(value = "/students/{dateFrom}/{dateTo}")
    @ResponseStatus(HttpStatus.OK)
    Collection<StudentDTO> getFilteredStudentsDTORest(@PathVariable(value = "dateFrom") Long dateFrom, @PathVariable(value = "dateTo") Long dateTo) throws ParseException {
        Date dateFromsql = new Date(dateFrom.longValue());
        Date dateTosql = new Date(dateTo.longValue());
        LOGGER.debug("StudentRestController getFilteredStudentsDTORest dateFrom - {},dateTo-", dateFromsql, dateTosql);
        Collection<StudentDTO> studentDTOS = studentService.getFilteredStudentsDTO(dateFromsql, dateTosql);
        return studentDTOS;
    }

    @GetMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Student getStudentByIdRest(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("StudentRestController getStudentByIdRest - {}", id);
        Student student = studentService.getStudentById(id);
        return student;
    }

    @PostMapping(value = "/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudentRest(@RequestBody Student student) {
        LOGGER.debug("StudentRestController addStudentRest - {}", student);
        Student studentOut = studentService.addStudent(student);
        return studentOut;
    }

    @PostMapping(value = "/students/id")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@RequestBody Student student) {
        LOGGER.debug("StudentRestController updateStudent - {}", student);
        studentService.updateStudent(student);
    }

    @DeleteMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeStudent(@PathVariable(value = "id") Integer id) {
        LOGGER.debug("StudentRestController removeStudent - {}", id);
        studentService.removeStudent(id);
    }
}
