package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.service.GroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;


@Controller
public class GroupController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    GroupService groupService;

    @GetMapping(value = "/groups")
    public String groups (final Model model)
    {
        LOGGER.debug("GroupController groups");
        Collection<GroupDTO> groupDTOS= groupService.getallGroupsDTO();
        model.addAttribute("groups",groupDTOS);
        return "groups";
    }


}
