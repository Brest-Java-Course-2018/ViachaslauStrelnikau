package com.epam.brest.rest;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.service.GroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Collection<GroupDTO> getGroupsDTO()
    {
        LOGGER.debug("GroupRestController getGroupsDTO");
        Collection<GroupDTO> groupDTOS=groupService.getallGroupsDTO();
        return groupDTOS;
    }
}
