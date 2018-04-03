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

@RestController
public class GroupRestController {
    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private GroupService groupService;

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "/groups")
    @ResponseStatus(HttpStatus.OK)
    public Collection<GroupDTO> getGroupsDTOrest()
    {
        LOGGER.debug("GroupRestController getGroupsDTOrest");
        Collection<GroupDTO> groupDTOS=groupService.getallGroupsDTO();
        return groupDTOS;
    }
    @GetMapping(value = "/groupsdto")
    @ResponseStatus(HttpStatus.OK)
    public Collection<GroupDTOlite> getallGroupsDTOliterest()
    {
        LOGGER.debug("GroupRestController getallGroupsDTOliterest");
        Collection<GroupDTOlite> groupDTOlites = groupService.getallGroupsDTOlite();
        return groupDTOlites;
    }
    @GetMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Group getGroupByIdrest(@PathVariable(value ="") Integer id)
    {
        LOGGER.debug("GroupRestController getGroupByIdrest - {}",id);
        Group group = groupService.getGroupById(id);
        return group;
    }
    @PostMapping(value = "/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Group addGrouprest(@RequestBody Group group)
    {
        LOGGER.debug("GroupRestController addGrouprest - {}",group);
        Group groupOut=groupService.addGroup(group);
        return groupOut;
    }
    @PostMapping(value = "/groups/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGrouprest(@PathVariable(value = "id")Integer id, @RequestBody Group group)
    {
        LOGGER.debug("GroupRestController updateGrouprest - {}",group);
        groupService.updateGroup(group);
    }
    @DeleteMapping (value = "/groups/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void removeGrouprest(@PathVariable(value = "id")Integer id)
    {
        LOGGER.debug("GroupRestController removeGrouprest - {}",id);
        groupService.removeGroup(id);
    }
}
