package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.model.Group;
import com.epam.brest.service.GroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Group MVC controller.
 */
@Controller
public class GroupController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    GroupService groupService;

    /**
     * Method groups mapping /groups get request to display groupDTO records.
     * @param model model data
     * @return view string
     */
    @GetMapping(value = "/groups")
    public String groups (final Model model)
    {
        LOGGER.debug("GroupController - groups");
        Collection<GroupDTO> groupDTOS= groupService.getallGroupsDTO();
        model.addAttribute("groups",groupDTOS);
        return "groups";
    }

    /**
     * Method editGroup mapping /groups/{id} get request.
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/groups/{id}")
    public String editGroup(@PathVariable(value = "id") Integer id, Model model)
    {
        LOGGER.debug("GroupController - editGroup:{}",id);
        Group group = groupService.getGroupById(id);
        model.addAttribute("isNew",false);
        model.addAttribute("group",group);
        return "editgroups";
    }
    @PostMapping(value = "/groups/{id}")
    public String updateGroup(@Valid Group group,BindingResult result,Model model)
    {
        LOGGER.debug("GroupController - updateGroup:{}",group);
        if(result.hasErrors())
        {
            LOGGER.error("GroupController - updateGroup:{}",group);
            model.addAttribute("group",group);
            model.addAttribute("isNew",false);
            return "editgroups";
        }
        else {
            groupService.updateGroup(group);
            return "redirect:/groups";
        }
    }
    @GetMapping(value = "/addGroup")
    public String newGroup( Model model)
    {
        LOGGER.debug("GroupController - newGroup:{}");
        Group group= new Group();
        model.addAttribute("group",group);
        model.addAttribute("isNew",true);
        return "editgroups";
    }
    @PostMapping(value = "/addGroup")
    public String addGroup(@Valid Group group,BindingResult result,Model model)
    {
        LOGGER.debug("GroupController - addGroup:{}",group);
        if(result.hasErrors())
        {
            LOGGER.error("GroupController - addGroup:{}",group);
            model.addAttribute("group",group);
            model.addAttribute("isNew",true);
            return "editgroups";
        }
        else {
            groupService.addGroup(group);
            return "redirect:/groups";
        }
    }

    @GetMapping(value = "/groups/{id}/delete")
    public String deleteGroup(@PathVariable(value = "id") Integer id)
    {
        groupService.removeGroup(id);
        return "redirect:/groups";
    }
}
