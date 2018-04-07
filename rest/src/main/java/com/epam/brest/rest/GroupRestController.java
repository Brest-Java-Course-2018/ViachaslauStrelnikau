package com.epam.brest.rest;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.model.Group;
import com.epam.brest.service.GroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Class GroupRestController implements group requests mapping methods.
 */
@RestController
public class GroupRestController {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * groupService property.
     */
    @Autowired
    private GroupService groupService;

    /**
     * setGroupService setter for groupService property.
     *
     * @param groupService groupService
     */
//    public void setGroupService(final GroupService groupService) {
//        this.groupService = groupService;
//    }

    /**
     * getGroupsDTOrest method maps get request to get all group DTO records.
     *
     * @return Collection of GroupDTO objects
     */
    @GetMapping(value = "/groups")
    @ResponseStatus(HttpStatus.OK)
    public Collection<GroupDTO> getGroupsDTOrest() {
        LOGGER.debug("GroupRestController getGroupsDTOrest");
        Collection<GroupDTO> groupDTOS =
                groupService.getallGroupsDTO();
        return groupDTOS;
    }

    /**
     * getallGroupsDTOliterest method maps get request to get all group DTO lite records.
     *
     * @return Collection GroupDTOlite objects
     */
    @GetMapping(value = "/groupsdto")
    @ResponseStatus(HttpStatus.OK)
    public Collection<GroupDTOlite> getallGroupsDTOliterest() {
        LOGGER.debug("GroupRestController getallGroupsDTOliterest");
        Collection<GroupDTOlite> groupDTOlites =
                groupService.getallGroupsDTOlite();
        return groupDTOlites;
    }

    /**
     * getGroupByIdrest method maps get request to get  group records by its id.
     *
     * @param id of group record
     * @return Group object
     */
    @GetMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Group getGroupByIdrest(@PathVariable(value = "") final Integer id) {
        LOGGER.debug("GroupRestController getGroupByIdrest - {}", id);
        Group group = groupService.getGroupById(id);
        return group;
    }

    /**
     * addGrouprest method maps post request to add group record to database.
     *
     * @param group record to add to database
     * @return Group added group record
     */
    @PostMapping(value = "/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Group addGrouprest(@RequestBody final Group group) {
        LOGGER.debug("GroupRestController addGrouprest - {}", group);
        Group groupOut = groupService.addGroup(group);
        return groupOut;
    }

    /**
     * updateGrouprest method that maps post request to update group record in the database.
     *
     * @param id    id of updating record
     * @param group updated record
     */
    @PostMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGrouprest(@PathVariable(value = "id") final Integer id, @RequestBody Group group) {
        LOGGER.debug("GroupRestController updateGrouprest - {}", group);
        groupService.updateGroup(group);
    }

    /**
     * removeGrouprest method maps delete request to remove group record from database.
     *
     * @param id of record to remove
     */
    @DeleteMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void removeGrouprest(@PathVariable(value = "id") final Integer id) {
        LOGGER.debug("GroupRestController removeGrouprest - {}", id);
        groupService.removeGroup(id);
    }
}
