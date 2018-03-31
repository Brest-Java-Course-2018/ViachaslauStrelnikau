package com.epam.brest.dao;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<StudentDTO> getallStudentsDTO();
    Student getStudentById(int id);
    Student addStudent(Student student);
    void updateStudent(Student student);
    void removeStudent(int id);
}
