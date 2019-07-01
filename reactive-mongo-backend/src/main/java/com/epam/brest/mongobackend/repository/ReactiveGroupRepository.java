package com.epam.brest.mongobackend.repository;

import com.epam.brest.mongobackend.model.Group;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@CrossOrigin(origins="*")
public interface ReactiveGroupRepository extends ReactiveMongoRepository<Group, ObjectId> {

    Mono<Group> findByGroupId(String id);
}
