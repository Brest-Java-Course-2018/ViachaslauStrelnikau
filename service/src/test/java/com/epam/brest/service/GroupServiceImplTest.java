package com.epam.brest.service;

import com.epam.brest.dao.GroupDao;
import com.epam.brest.dto.GroupDTO;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class GroupServiceImplTest {

    @Autowired
    GroupService groupService;

    @Autowired
    private GroupDao mockGroupDao;

    private static GroupDTO groupDTO;
    private static GroupDTO groupDTO2;

    @Before
    public void testSetUp() {
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
    }

    @After
    public void TestClear() {
        EasyMock.verify(mockGroupDao);
    }

    @Test
    public void getAllDTOServiceTest() {
        EasyMock.expect(mockGroupDao.getallGroupsDTO()).andReturn(Arrays.asList(groupDTO, groupDTO2));
        EasyMock.replay(mockGroupDao);

        groupService.getallGroupsDTO();

    }

}