package com.epam.brest.mongobackend.repository;

import com.epam.brest.mongobackend.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactiveStudentRepository extends ReactiveMongoRepository<Student, ObjectId> {
}
