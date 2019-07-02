package com.epam.brest.mongobackend.rest;

import com.epam.brest.mongobackend.errors.NotFoundInDBException;
import com.epam.brest.mongobackend.model.Group;
import com.epam.brest.mongobackend.model.GroupDTOlite;
import com.epam.brest.mongobackend.model.GroupDto;
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


@RestController
@CrossOrigin(origins = "*")
public class GroupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ReactiveGroupRepository groupRepository;
    private ReactiveStudentRepository studentRepository;

    @Autowired
    public GroupController(ReactiveGroupRepository groupRepository, ReactiveStudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Group> getAllGroups() {
        logger.info("getAllGroups");
        return groupRepository.findAll();
    }

    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Group> findGroupById(@PathVariable(value = "id") final String id) {
        logger.info("findGroupById");
        return groupRepository.findByGroupId(id)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Not Found in DB")));
    }

    @GetMapping("/groupsdto")
    @ResponseStatus(HttpStatus.OK)
    public Flux<GroupDTOlite> getallGroupsDTOliterest() {
        logger.info("getallGroupsDTOliterest");
        return groupRepository.findAll()
                .map(group -> new GroupDTOlite(group.getGroupId(), group.getFullName()));
    }

    @PostMapping(value = "/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Group> addGrouprest(@RequestBody final Group group) {
        logger.info("addGrouprest");
        return groupRepository.save(group);
    }

    @PostMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> updateGrouprest(@PathVariable(value = "id") final String id, @RequestBody GroupDto group) {
        logger.info("updateGrouprest");
        return groupRepository.findByGroupId(id)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Not Found in DB")))
                .flatMap(group1 -> updateGroupRecord(group1, group))
                .flatMap(group1 -> groupRepository.save(group1))
                .then();
    }

    /**
     * removeGrouprest method maps delete request to remove group record from database.
     *
     * @param id of record to remove
     */
    @DeleteMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> removeGrouprest(@PathVariable(value = "id") final String id) {
        logger.info("removeGrouprest");
        return groupRepository.findByGroupId(id)
                .flatMap(group -> groupRepository.delete(group))
                .then();
    }

    private Mono<Group> updateGroupRecord(Group source, GroupDto changes) {
        logger.info("updateGroupRecord");
        source.setDescription(changes.getDescription());
        source.setFullName(changes.getFullName());
        source.setShortName(changes.getShortName());

        for (Student student : source.getStudents()) {
            student.setFullName(changes.getFullName());
            student.setGroupId(changes.getGroupId());

            studentRepository.findByStudentId(student.getStudentId())
                    .map(student1 ->
                    {
                        student1.setGroupId(student.getGroupId());
                        student1.setFullName(student.getFullName());
                        return studentRepository.save(student1);
                    });
        }

        return Mono.just(source);
    }
}
