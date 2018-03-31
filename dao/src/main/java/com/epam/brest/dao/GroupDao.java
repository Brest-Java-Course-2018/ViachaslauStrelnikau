package com.epam.brest.dao;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.model.Group;

import java.util.Collection;

public interface GroupDao {
    /**
     * method getallGroupsDTO gets all group DTO objects.
     *
     * @return Collection of group DTO
     */
    Collection<GroupDTO> getallGroupsDTO();

    /**
     * method getallGroupsDTOlite gets all group DTOlite objects.
     *
     * @return Collection of group DTOlite
     */
    Collection<GroupDTOlite> getallGroupsDTOlite();

    /**
     * method getGroupById returns group by its ID.
     *
     * @param id id of group
     * @return Group searched group
     */
    Group getGroupById(int id);

    /**
     * method addGroup addes Griup record to database.
     *
     * @param group group
     * @return Group added group
     */
    Group addGroup(Group group);

    /**
     * method updateGroup update Group record in database.
     *
     * @param group group
     */
    void updateGroup(Group group);

    /**
     * method deleteGroup remove Group record from database.
     *
     * @param groupId id of record to remove
     */
    void removeGroup(int groupId);
}

