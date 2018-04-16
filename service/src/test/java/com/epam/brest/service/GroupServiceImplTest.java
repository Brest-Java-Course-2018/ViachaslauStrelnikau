package com.epam.brest.service;

import com.epam.brest.dao.GroupDao;
import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.model.Group;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Test GroupServiceImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class GroupServiceImplTest {
    /**
     * GroupService object injection.
     */
    @Autowired
    private GroupService groupService;
    /**
     * GroupDao object injection.
     */
    @Autowired
    private GroupDao mockGroupDao;

    private static Group group;
    private static Group group2;

    private static GroupDTO groupDTO;
    private static GroupDTO groupDTO2;

    private static GroupDTOlite groupDTOlite;
    private static GroupDTOlite groupDTOlite2;
    /**
     * Test set up.
     */
    @Before
    public void testSetUp() {
        group = new Group();
        group.setFullName("Test1");
        group.setShortName("T1");
        group.setDescription("Test");
        group2 = new Group();
        group2.setGroupId(1);
        group2.setFullName("Test1");
        group2.setShortName("T1");
        group2.setDescription("Test");


        groupDTO = new GroupDTO();
        groupDTO.setGroupId(1);
        groupDTO.setFullName("Test1");
        groupDTO.setGroupAvgMarks(1.1);
        groupDTO.setShortName("T1");

        groupDTO2 = new GroupDTO();
        groupDTO2.setGroupId(2);
        groupDTO2.setFullName("Test2");
        groupDTO2.setGroupAvgMarks(2.2);
        groupDTO2.setShortName("T2");

        groupDTOlite = new GroupDTOlite();
        groupDTOlite.setGroupId(1);
        groupDTOlite.setFullName("Test1");
        groupDTOlite2 = new GroupDTOlite();
        groupDTOlite2.setGroupId(2);
        groupDTOlite2.setFullName("Test2");
    }
    /**
     * Test clean.
     */
    @After
    public void TestClear() {
        EasyMock.verify(mockGroupDao);
        EasyMock.reset(mockGroupDao);
    }
    /**
     * getallGroupsDTO method test.
     */
    @Test
    public void getAllDTOServiceTest() {
        EasyMock.expect(mockGroupDao.getallGroupsDTO()).andReturn(Arrays.asList(groupDTO, groupDTO2));
        EasyMock.replay(mockGroupDao);

        groupService.getallGroupsDTO();
    }
    /**
     * getallGroupsDTOlite method test.
     */
    @Test
    public void getallGroupsDTOliteTest() {
        EasyMock.expect(mockGroupDao.getallGroupsDTOlite()).andReturn(Arrays.asList(groupDTOlite, groupDTOlite2));
        EasyMock.replay(mockGroupDao);

        groupService.getallGroupsDTOlite();
    }
    /**
     * getGroupById method test.
     */
    @Test
    public void getGroupByIdTest() {
        EasyMock.expect(mockGroupDao.getGroupById(1)).andReturn(group);
        EasyMock.replay(mockGroupDao);

        groupService.getGroupById(1);
    }
    /**
     * addGroup method test.
     */
    @Test
    public void addGroupTest() {
        EasyMock.expect(mockGroupDao.addGroup(group)).andReturn(group2);
        EasyMock.replay(mockGroupDao);

        groupService.addGroup(group);

    }
    /**
     * updateGroup method test.
     */
    @Test
    public void updateGroupTest() {
        mockGroupDao.updateGroup(group);
        EasyMock.replay(mockGroupDao);

        groupService.updateGroup(group);
    }
    /**
     * removeGroup method test.
     */
    @Test
    public void removeGroup() {
        mockGroupDao.removeGroup(1);
        EasyMock.replay(mockGroupDao);

        groupService.removeGroup(1);
    }
}