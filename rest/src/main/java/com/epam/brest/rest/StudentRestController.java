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
/**
 * Class StudentRestController implements student requests mapping methods.
 */
@RestController
public class StudentRestController {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * studentService property.
     */
    @Autowired
    private StudentService studentService;
    /**
     * setStudentService setter for studentService property.
     *
     * @param studentService studentService
     */
    public void setStudentService(final StudentService studentService) {
        this.studentService = studentService;
    }
    /**
     * getAllStudentsRest method maps get request to get all student DTO records.
     *
     * @return Collection of studentDTO objects
     */
    @GetMapping(value = "/students")
    @ResponseStatus(HttpStatus.OK)
    Collection<StudentDTO> getAllStudentsRest() {
        LOGGER.debug("StudentRestController getAllStudentsRest");
        Collection<StudentDTO> studentDTOS = studentService.getallStudentsDTO();
        return studentDTOS;
    }
    /**
     * getFilteredStudentsDTORest method maps get request to get student DTO records in entered period.
     *
     * @return Collection StudentDTO objects
     */
    @GetMapping(value = "/students/{dateFrom}/{dateTo}")
    @ResponseStatus(HttpStatus.OK)
    Collection<StudentDTO> getFilteredStudentsDTORest(@PathVariable(value = "dateFrom") final Long dateFrom, @PathVariable(value = "dateTo") final Long dateTo) throws ParseException {
        Date dateFromsql = new Date(dateFrom.longValue());
        Date dateTosql = new Date(dateTo.longValue());
        LOGGER.debug("StudentRestController getFilteredStudentsDTORest dateFrom - {},dateTo-", dateFromsql, dateTosql);
        Collection<StudentDTO> studentDTOS = studentService.getFilteredStudentsDTO(dateFromsql, dateTosql);
        return studentDTOS;
    }
    /**
     * getStudentByIdRest method maps get request to get  student record by its id.
     *
     * @param id of student record
     * @return student object
     */
    @GetMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Student getStudentByIdRest(@PathVariable(value = "id") final Integer id) {
        LOGGER.debug("StudentRestController getStudentByIdRest - {}", id);
        Student student = studentService.getStudentById(id);
        return student;
    }
    /**
     * addStudentRest method maps post request to add student record to database.
     *
     * @param student record to add to database
     * @return Student added student record
     */
    @PostMapping(value = "/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudentRest(@RequestBody final Student student) {
        LOGGER.debug("StudentRestController addStudentRest - {}", student);
        Student studentOut = studentService.addStudent(student);
        return studentOut;
    }
    /**
     * updateStudent method that maps post request to update student record in the database.
     *
     * @param id   id of updating record
     * @param student updated record
     */
    @PostMapping(value = "/students/id")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@RequestBody final Student student,@PathVariable(value = "id") final Integer id) {
        LOGGER.debug("StudentRestController updateStudent - {}", student);
        studentService.updateStudent(student);
    }
    /**
     * removeStudent method maps delete request to remove student record from database.
     *
     * @param id of record to remove
     */
    @DeleteMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeStudent(@PathVariable(value = "id") final Integer id) {
        LOGGER.debug("StudentRestController removeStudent - {}", id);
        studentService.removeStudent(id);
    }
}
