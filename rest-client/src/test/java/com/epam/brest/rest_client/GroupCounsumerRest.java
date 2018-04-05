package com.epam.brest.rest_client;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.model.Group;
import com.epam.brest.service.GroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class GroupCounsumerRest implements GroupService {

    private static final Logger LOGGER= LogManager.getLogger();
    private String url;
    private RestTemplate restTemplate;

    public GroupCounsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<GroupDTO> getallGroupsDTO() {
        LOGGER.debug("GroupCounsumerRest getallGroupsDTO");
        ResponseEntity<Collection> responseEntity = restTemplate.getForEntity(url,Collection.class);
        Collection<GroupDTO> groupDTOs = (Collection<GroupDTO>)responseEntity.getBody();
        return groupDTOs;
    }

    @Override
    public Collection<GroupDTOlite> getallGroupsDTOlite() {
        LOGGER.debug("GroupCounsumerRest getallGroupsDTO");
        ResponseEntity<Collection> responseEntity = restTemplate.getForEntity(url,Collection.class);
        Collection<GroupDTOlite> groupDTOlites = (Collection<GroupDTOlite>)responseEntity.getBody();
        return groupDTOlites;
    }

    @Override
    public Group getGroupById(int id) {
        LOGGER.debug("GroupCounsumerRest getGroupById - {}",id);
        ResponseEntity responseEntity=restTemplate.getForEntity(url+"/"+id,Group.class);
        Group group=(Group) responseEntity.getBody();
        return group;
    }

    @Override
    public Group addGroup(Group group) {
        LOGGER.debug("GroupCounsumerRest addGroup - {}",group);
        ResponseEntity responseEntity=restTemplate.postForEntity(url,group,Group.class);
        Group group1= (Group) responseEntity.getBody();
        return group1;
    }

    @Override
    public void updateGroup(Group group) {
        LOGGER.debug("GroupCounsumerRest updateGroup - {}",group);
        restTemplate.postForEntity(url+"/"+group.getGroupId(),group,Group.class);
    }

    @Override
    public void removeGroup(int groupId) {

        LOGGER.debug("GroupCounsumerRest removeGroup - {}",groupId);
        restTemplate.delete(url+"/"+groupId);
    }
}
