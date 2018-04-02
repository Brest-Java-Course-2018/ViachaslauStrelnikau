package com.epam.brest.service;

import com.epam.brest.dao.GroupDao;
import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.model.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Class GroupServiceImpl provides a group service implementation.
 */
public class GroupServiceImpl implements GroupService {
    /**
     * Logger initilization.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * groupDao property.
     */
    @Autowired
    private GroupDao groupDao;

    /**
     * method GroupServiceImpl constructor.
     *
     * @param groupDao DAO of group objects
     */
    public GroupServiceImpl(final GroupDao groupDao) {
        this.groupDao = groupDao;
    }
    /**
     * method getallGroupsDTO gets all group DTO objects.
     *
     * @return Collection of group DTO
     */
    @Override
    public Collection<GroupDTO> getallGroupsDTO() {
        LOGGER.debug("GroupService getallGroupsDTO");
        Collection<GroupDTO> groupDTOS = groupDao.getallGroupsDTO();
        return groupDTOS;
    }
    /**
     * method getallGroupsDTOlite gets all group DTOlite objects.
     *
     * @return Collection of group DTOlite
     */
    @Override
    public Collection<GroupDTOlite> getallGroupsDTOlite() {
        LOGGER.debug("GroupService getallGroupsDTOlite");
        Collection<GroupDTOlite> groupDTOlites = groupDao.getallGroupsDTOlite();
        return groupDTOlites;
    }
    /**
     * method getGroupById returns group by its ID.
     *
     * @param id id of group
     * @return Group searched group
     */
    @Override
    public Group getGroupById(final int id) {
        LOGGER.debug("GroupService getGroupById - {}", id);
        Group group = groupDao.getGroupById(id);
        return group;
    }
    /**
     * method addGroup addes Griup record to database.
     *
     * @param group group
     * @return Group added group
     */
    @Override
    public Group addGroup(final Group group) {
        LOGGER.debug("GroupService addGroup - {}", group);
        Group groupout = groupDao.addGroup(group);
        return groupout;
    }
    /**
     * method updateGroup update Group record in database.
     *
     * @param group group
     */
    @Override
    public void updateGroup(final Group group) {
        LOGGER.debug("GroupService updateGroup - {}", group);
        groupDao.updateGroup(group);
    }
    /**
     * method deleteGroup remove Group record from database.
     *
     * @param groupId id of record to remove
     */
    @Override
    public void removeGroup(final int groupId) {
        LOGGER.debug("GroupService removeGroup - {}", groupId);
        groupDao.removeGroup(groupId);
    }
}
