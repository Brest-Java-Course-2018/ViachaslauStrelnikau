package com.epam.brest.mongobackend.rest;

import com.epam.brest.mongobackend.model.Student;
import com.epam.brest.mongobackend.repository.ReactiveStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StudentController {

    private ReactiveStudentRepository studentRepository;

    @Autowired
    public StudentController(ReactiveStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    Flux<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }
}
