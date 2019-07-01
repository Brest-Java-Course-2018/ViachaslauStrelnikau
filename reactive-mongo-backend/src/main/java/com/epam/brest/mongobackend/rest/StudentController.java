package com.epam.brest.mongobackend.rest;

import com.epam.brest.mongobackend.errors.NotFoundInDBException;
import com.epam.brest.mongobackend.model.Student;
import com.epam.brest.mongobackend.repository.ReactiveGroupRepository;
import com.epam.brest.mongobackend.repository.ReactiveStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    private ReactiveStudentRepository studentRepository;
    private ReactiveGroupRepository groupRepository;

    @Autowired
    public StudentController(ReactiveStudentRepository studentRepository, ReactiveGroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/students")
    Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    Mono<Student> getStudentbyId(@PathVariable(value = "id") String id) {
        return studentRepository.findByStudentId(id)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Not found in DB")));
    }

    @PostMapping("/students")
    Mono<Void> addStudent(@RequestBody final Student student) {
        return groupRepository.findByGroupId(student.getGroupId())
                .map(group -> new Student(null, student.getStudentName(), student.getStudentBirth(), student.getStudentAvgMarks(), student.getGroupId(), group.getFullName()))
                .flatMap(student1 -> studentRepository.save(student1))
                .then();
    }

    @PostMapping("/students/{id}")
    Mono<Void> updateStudent(@PathVariable(value = "id") String id, @RequestBody Student student) {
        return groupRepository.findByGroupId(student.getGroupId())
                .flatMap(group -> {
                    student.setFullName(group.getFullName());
                    return Mono.just(student);
                })
                .flatMap(student1 -> studentRepository.findByStudentId(student.getStudentId())
                        .flatMap(student2 -> updateStudentRecord(student2, student1))
                        .flatMap(student2 -> studentRepository.save(student2))
                        .then());
    }

    @DeleteMapping("/students/{id}")
    Mono<Void> deleteStudent(@PathVariable(value = "id") String id) {
        return studentRepository.findByStudentId(id)
                .flatMap(
                        student -> studentRepository.delete(student)
                )
                .then();
    }


    private Mono<Student> updateStudentRecord(Student source, Student changes) {
        source.setStudentAvgMarks(changes.getStudentAvgMarks());
        source.setStudentBirth(changes.getStudentBirth());
        source.setStudentName(changes.getStudentName());
        source.setGroupId(changes.getGroupId());
        source.setFullName(changes.getFullName());
        return Mono.just(source);
    }
}
