package com.epam.brest.dao;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

/**
 * interface StudentDao.
 */
public interface StudentDao {
    /**
     * method getallStudentsDTO gets all students.
     *
     * @return Collection of student DTO
     */
    Collection<StudentDTO> getallStudentsDTO();

    /**
     * method getFilteredStudentsDTO gets all students which date of birth is beatwen entered dates.
     *
     * @param dateFrom begin date interval
     * @param dateTo   ebd date interval
     * @return Collection of student DTO
     * @throws ParseException data parse exception
     */
    Collection<StudentDTO> getFilteredStudentsDTO(final Date dateFrom,final Date dateTo) throws ParseException;

    /**
     * method getStudentById returns student by its ID.
     *
     * @param id id of student
     * @return Student searched student
     */
    Student getStudentById(final int id);

    /**
     * method addStudent addes student record to database.
     *
     * @param student student
     * @return Student added student
     */
    Student addStudent(final Student student);

    /**
     * method updateStudent updates student record in the database.
     *
     * @param student student
     */
    void updateStudent(final Student student);

    /**
     * method removeStudent remove student record from database.
     *
     * @param id id of record to remove
     */
    void removeStudent(final int id);
}
