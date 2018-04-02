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

public class StudentServiceImpl implements StudentService {

    private final static Logger LOGGER = LogManager.getLogger();
    @Autowired
    StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Collection<StudentDTO> getallStudentsDTO() {
        LOGGER.debug("StudentService getallStudentsDTO");
        Collection<StudentDTO> studentDTOS = studentDao.getallStudentsDTO();
        return studentDTOS;
    }

    @Override
    public Collection<StudentDTO> getFilteredStudentsDTO(final Date dateFrom, final Date dateTo) throws ParseException {
        LOGGER.debug("StudentService getFilteredStudentsDTO dateFrom-{}, dateTo -{}", dateFrom, dateTo);
        Collection<StudentDTO> studentDTOS = studentDao.getFilteredStudentsDTO(dateFrom, dateTo);
        return studentDTOS;
    }

    @Override
    public Student getStudentById(final int id) {
        LOGGER.debug("StudentService getStudentById -{}", id);
        Student student = studentDao.getStudentById(id);
        return student;
    }

    @Override
    public Student addStudent(final Student student) {
        LOGGER.debug("StudentService addStudent - {}", student);
        Student student_out = studentDao.addStudent(student);
        return student_out;
    }

    @Override
    public void updateStudent(final Student student) {
        LOGGER.debug("StudentService updateStudent - {}", student);
        studentDao.updateStudent(student);
    }

    @Override
    public void removeStudent(final int id) {
        LOGGER.debug("StudentService removeStudent - {}", id);
        studentDao.removeStudent(id);
    }
}
