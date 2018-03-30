package com.epam.brest.dao;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.model.Group;

import java.util.Collection;

public interface GroupDao {
    Collection<GroupDTO> getallGroupsDTO();
    Group getGroupById(int id);
    Group addGroup(Group group);
    void updateGroup(Group group);
    void deleteGroup(int id);
}

