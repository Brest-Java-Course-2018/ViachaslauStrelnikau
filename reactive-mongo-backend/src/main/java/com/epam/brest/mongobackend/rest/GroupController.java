package com.epam.brest.mongobackend.rest;

import com.epam.brest.mongobackend.errors.NotFoundInDBException;
import com.epam.brest.mongobackend.model.Group;
import com.epam.brest.mongobackend.model.GroupDTOlite;
import com.epam.brest.mongobackend.model.GroupDto;
import com.epam.brest.mongobackend.repository.ReactiveGroupRepository;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
    private ReactiveGroupRepository groupRepository;

    @Autowired
    public GroupController(ReactiveGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Group> getAllGroups() {
        LOGGER.debug("getAllGroups");
        return groupRepository.findAll();
    }

    @GetMapping("/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Group> findGroupById(@PathVariable(value = "id") final String id) {
        LOGGER.debug("findGroupById");
        return groupRepository.findByGroupId(id)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Not Found in DB")));
    }

    @GetMapping("/groupsdto")
    @ResponseStatus(HttpStatus.OK)
    public Flux<GroupDTOlite> getallGroupsDTOliterest() {
        LOGGER.debug("getallGroupsDTOliterest");
        return groupRepository.findAll()
                .map(group -> new GroupDTOlite(group.getGroupId(), group.getFullName()));
    }

    @PostMapping(value = "/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Group> addGrouprest(@RequestBody final Group group) {
        LOGGER.debug("addGrouprest");
        return groupRepository.save(group);
    }

    @PostMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> updateGrouprest(@PathVariable(value = "id") final String id, @RequestBody GroupDto group) {
        LOGGER.debug("updateGrouprest");
        return groupRepository.findByGroupId(id)
                .switchIfEmpty(Mono.error(new NotFoundInDBException("Not Found in DB")))
                .flatMap(group1 ->updateGroupRecord(group1,group))
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
       return groupRepository.findByGroupId(id)
                .flatMap(group -> groupRepository.delete(group))
                .then();
    }

    private Mono<Group> updateGroupRecord(Group source, GroupDto changes)
    {
        source.setDescription(changes.getDescription());
        source.setFullName(changes.getFullName());
        source.setShortName(changes.getShortName());
        return Mono.just(source);
    }
}
