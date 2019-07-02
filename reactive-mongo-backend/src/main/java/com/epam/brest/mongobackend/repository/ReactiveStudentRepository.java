package com.epam.brest.mongobackend.repository;

import com.epam.brest.mongobackend.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;


@CrossOrigin(origins="*")
public interface ReactiveStudentRepository extends ReactiveMongoRepository<Student, ObjectId> {
    Mono<Student> findByStudentId(String id);
    Flux<Student> findByStudentBirthBetween(Date from, Date to);
    Flux<Student> findByStudentBirthGreaterThan(Date from);
    Flux<Student> findByStudentBirthLessThan(Date to);
}
