package com.epam.brest.mongobackend.rest;

import com.epam.brest.mongobackend.model.Group;
import com.epam.brest.mongobackend.repository.ReactiveGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
public class GroupController {


    private ReactiveGroupRepository groupRepository;

    @Autowired
    public GroupController(ReactiveGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    public Flux<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
