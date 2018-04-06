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

/**
 * Class GroupCounsumerRest implements GroupService to fully compatible with rest service.
 */
public class GroupCounsumerRest implements GroupService {

    private static final Logger LOGGER = LogManager.getLogger();
    private String url;
    private RestTemplate restTemplate;

    /**
     * Constructor of class GroupCounsumerRest.
     *
     * @param url          -url of request
     * @param restTemplate rest template interact with rest service
     */
    public GroupCounsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * getallGroupsDTO method gets collection of GroupsDTO through rest service.
     *
     * @return Collection of GroupsDTO objects
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<GroupDTO> getallGroupsDTO() {
        LOGGER.debug("GroupCounsumerRest getallGroupsDTO");
        ResponseEntity<Collection> responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<GroupDTO> groupDTOs = (Collection<GroupDTO>) responseEntity.getBody();
        return groupDTOs;
    }

    /**
     * getallGroupsDTOlite method gets collection of GroupDTOlite through rest service.
     *
     * @return Collection of GroupDTOlite objects
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<GroupDTOlite> getallGroupsDTOlite() {
        LOGGER.debug("GroupCounsumerRest getallGroupsDTO");
        ResponseEntity<Collection> responseEntity =
                restTemplate.getForEntity(url, Collection.class);
        Collection<GroupDTOlite> groupDTOlites =
                (Collection<GroupDTOlite>) responseEntity.getBody();
        return groupDTOlites;
    }

    /**
     * getGroupById method get Student record object through rest service by its id.
     *
     * @param id id of group
     * @return Group object
     */
    @Override
    public Group getGroupById(int id) {
        LOGGER.debug("GroupCounsumerRest getGroupById - {}", id);
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url + "/" + id, Group.class);
        Group group = (Group) responseEntity.getBody();
        return group;
    }

    /**
     * addGroup method add Group record to database throgh rest service.
     *
     * @param group group record
     * @return Group record that was added
     */
    @Override
    public Group addGroup(Group group) {
        LOGGER.debug("GroupCounsumerRest addGroup - {}", group);
        ResponseEntity responseEntity =
                restTemplate.postForEntity(url, group, Group.class);
        Group group1 = (Group) responseEntity.getBody();
        return group1;
    }

    /**
     * updateGroup method update Group record in the database through rest service.
     *
     * @param group updated group record
     */
    @Override
    public void updateGroup(Group group) {
        LOGGER.debug("GroupCounsumerRest updateGroup - {}", group);
        restTemplate
                .postForEntity(url + "/" + group.getGroupId(), group, Group.class);
    }

    /**
     * removeGroup method remove Group record from the database through rest service.
     *
     * @param groupId id of record to remove
     */
    @Override
    public void removeGroup(int groupId) {

        LOGGER.debug("GroupCounsumerRest removeGroup - {}", groupId);
        restTemplate.delete(url + "/" + groupId);
    }
}
