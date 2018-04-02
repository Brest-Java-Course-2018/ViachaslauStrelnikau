package com.epam.brest.service;

import com.epam.brest.dao.StudentDao;
import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;
/**
 * Class StudentService provedes a student service implementation.
 */
public class StudentServiceImpl implements StudentService {
    /**
     * Logger initilization.
     */
    private static final  Logger LOGGER = LogManager.getLogger();
    /**
     * studentDao property.
     */
    @Autowired
    StudentDao studentDao;

    /**
     * method StudentServiceImpl constructor.
     *
     * @param studentDao DAO of student objects
     */
    public StudentServiceImpl(final StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * method getallStudentsDTO gets all students.
     *
     * @return Collection of student DTO
     */
    @Override
    public Collection<StudentDTO> getallStudentsDTO() {
        LOGGER.debug("StudentService getallStudentsDTO");
        Collection<StudentDTO> studentDTOS = studentDao.getallStudentsDTO();
        return studentDTOS;
    }

    /**
     * method getFilteredStudentsDTO gets all students which date of birth is beatwen entered dates.
     *
     * @param dateFrom begin date interval
     * @param dateTo   ebd date interval
     * @return Collection of student DTO
     * @throws ParseException data parse exception
     */
    @Override
    public Collection<StudentDTO> getFilteredStudentsDTO(final Date dateFrom, final Date dateTo) throws ParseException {
        LOGGER.debug("StudentService getFilteredStudentsDTO dateFrom-{}, dateTo -{}", dateFrom, dateTo);
        Collection<StudentDTO> studentDTOS = studentDao.getFilteredStudentsDTO(dateFrom, dateTo);
        return studentDTOS;
    }

    /**
     * method getStudentById returns student by its ID.
     *
     * @param id id of student
     * @return Student searched student
     */
    @Override
    public Student getStudentById(final int id) {
        LOGGER.debug("StudentService getStudentById -{}", id);
        Student student = studentDao.getStudentById(id);
        return student;
    }

    /**
     * method addStudent addes student record to database.
     *
     * @param student student
     * @return Student added student
     */
    @Override
    public Student addStudent(final Student student) {
        LOGGER.debug("StudentService addStudent - {}", student);
        Student studentout = studentDao.addStudent(student);
        return studentout;
    }

    /**
     * method updateStudent updates student record in the database.
     *
     * @param student student
     */
    @Override
    public void updateStudent(final Student student) {
        LOGGER.debug("StudentService updateStudent - {}", student);
        studentDao.updateStudent(student);
    }

    /**
     * method removeStudent remove student record from database.
     *
     * @param id id of record to remove
     */
    @Override
    public void removeStudent(final int id) {
        LOGGER.debug("StudentService removeStudent - {}", id);
        studentDao.removeStudent(id);
    }
}
