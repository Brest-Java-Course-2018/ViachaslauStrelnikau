package com.epam.brest.mongobackend.rest;

import com.epam.brest.mongobackend.errors.NotFoundInDBException;
import com.epam.brest.mongobackend.model.Group;
import com.epam.brest.mongobackend.model.Student;
import com.epam.brest.mongobackend.repository.ReactiveGroupRepository;
import com.epam.brest.mongobackend.repository.ReactiveStudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Date;


@RestController
@CrossOrigin(origins = "*")
public class StudentController {

    private ReactiveStudentRepository studentRepository;
    private ReactiveGroupRepository groupRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StudentController(ReactiveStudentRepository studentRepository, ReactiveGroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/students")
    Flux<Student> getAllStudents() {
        logger.info("getAllStudents");
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    Mono<Student> getStudentbyId(@PathVariable(value = "id") String id) {
        logger.info("getStudentbyId");
        logger.info("id:" + id);
        return studentRepository.findByStudentId(id)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Not found in DB")));
    }

    @PostMapping("/students")
    Mono<Void> addStudent(@RequestBody final Student student) {
        logger.info("addStudent");
        logger.info(student.toString());
        return groupRepository.findByGroupId(student.getGroupId())
                .map(group -> new Student(null, student.getStudentName(), student.getStudentBirth(), student.getStudentAvgMarks(), student.getGroupId(), group.getFullName()))
                .flatMap(student1 -> studentRepository.save(student1))
                .then();
    }

    @PostMapping("/students/{id}")
    Mono<Void> updateStudent(@PathVariable(value = "id") String id, @RequestBody Student student) {
        logger.info("updateStudent");
        logger.info("id:" + id);
        logger.info(student.toString());
        return groupRepository.findByGroupId(student.getGroupId())
                .flatMap(group -> {
                    student.setFullName(group.getFullName());
                    return Mono.just(student);
                })
                .flatMap(student1 -> studentRepository.findByStudentId(id)
                        .log()
                        .flatMap(student2 -> updateStudentRecord(student2, student1))
                        .flatMap(student2 -> studentRepository.save(student2))
                        .then());
    }

    @DeleteMapping("/students/{id}")
    Mono<Void> deleteStudent(@PathVariable(value = "id") String id) {
        logger.info("deleteStudent");
        logger.info("id:" + id);
        return studentRepository.findByStudentId(id)
                .flatMap(student -> {
                    deleteFromGroupAndRecountAvgMarks(student.getGroupId(), id);
                    return Mono.just(student);
                })
                .flatMap(student -> studentRepository.delete(student))
                .then();
    }

    /**
     * getFilteredStudentsDTORest method maps get request to get student DTO records in entered period.
     *
     * @return Collection StudentDTO objects
     */
    @GetMapping(value = "/students/{dateFrom}/{dateTo}")
    @ResponseStatus(HttpStatus.OK)
    Flux<Student> getFilteredStudents(@PathVariable(value = "dateFrom") final Long dateFrom, @PathVariable(value = "dateTo") final Long dateTo) {
        logger.info("getFilteredStudents");
        Date dateFromsql = new Date(0);
        Date dateTosql = new Date();
        if (dateFrom != 0)
            dateFromsql = new Date(dateFrom);
        if (dateTo != 0)
            dateTosql = new Date(dateTo);

        return studentRepository.findByStudentBirthBetween(dateFromsql, dateTosql);
    }

    private Mono<Student> updateStudentRecord(Student source, Student changes) {
        logger.info("old:" + source);
        logger.info("new:" + changes);

        source.setStudentBirth(changes.getStudentBirth());
        source.setStudentName(changes.getStudentName());

        if (!source.getGroupId().equals(changes.getGroupId())) {
            source.setStudentAvgMarks(changes.getStudentAvgMarks());
            deleteAndAddFromGroupAndRecountAvgMarks(changes.getGroupId(), source);
            source.setGroupId(changes.getGroupId());
            source.setFullName(changes.getFullName());

        }
        if (source.getStudentAvgMarks() != changes.getStudentAvgMarks()) {
            source.setStudentAvgMarks(changes.getStudentAvgMarks());
        }

        return Mono.just(source);
    }

    private Student findInaGroup(Group group, String studentId) {
        for (Student student : group.getStudents()) {
            if (student.getStudentId().equals(studentId))
                return student;
        }
        return new Student();
    }

    private void deleteFromGroupAndRecountAvgMarks(String groupID, String stId) {
        logger.info("deleteFromGroupAndRecountAvgMarks");
        logger.info("groupID:" + groupID + ":" + "stId:" + stId);
        groupRepository.findByGroupId(groupID)
                .flatMap(group -> {
                    group.getStudents().remove(findInaGroup(group, stId));
                    group.updateAvgMark();
                    return Mono.just(group);
                })
                .flatMap(group -> groupRepository.save(group))
                .subscribe();
    }

    private void deleteAndAddFromGroupAndRecountAvgMarks(String groupID, Student student) {
        logger.info("deleteFromGroupAndRecountAvgMarks");
        logger.info("groupID:" + groupID + ":" + "Student:" + student);
         groupRepository.findByGroupId(student.getGroupId())
                .flatMap(group -> {
                    group.getStudents().remove(findInaGroup(group, student.getStudentId()));
                    group.updateAvgMark();
                    return Mono.just(group);
                })
                .flatMap(group -> groupRepository.save(group))
                .and(
                        groupRepository.findByGroupId(groupID)
                                .flatMap(group2 -> {
                                    group2.getStudents().add(student);
                                    group2.updateAvgMark();
                                    return Mono.just(group2);
                                })
                                .flatMap(group2 -> groupRepository.save(group2))

                )
                .subscribe();
    }
}
