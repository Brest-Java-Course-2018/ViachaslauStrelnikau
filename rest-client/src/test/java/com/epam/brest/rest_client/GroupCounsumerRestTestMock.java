package com.epam.brest.rest_client;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
import com.epam.brest.model.Group;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-restclient-context.xml")
public class GroupCounsumerRestTestMock {

    @Autowired
    private GroupCounsumerRest groupservice;

    @Autowired
    private RestTemplate restTemplateMock;

    private static int ID = 22;
    private static Group group;
    private static GroupDTO groupDTO;
    private static GroupDTO groupDTO2;

    private static GroupDTOlite groupDTOlite;
    private static GroupDTOlite groupDTOlite2;

    /**
     * testSetUp test setUp method.
     */
    @Before
    public void testSetUp() {
        group = new Group();
        group.setGroupId(11);
        group.setFullName("Test1");
        group.setShortName("T1");
        group.setDescription("Test");

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
     * tearDown  test end method.
     */
    @After
    public void tearDown() {
        verify(restTemplateMock);
        reset(restTemplateMock);
    }

    /**
     * getallGroupsDTO mock test
     */
    @Test
    public void getallGroupsDTO() {

        Collection<GroupDTO> groupDTOS = Arrays.asList(groupDTO, groupDTO2);
        ResponseEntity responseEntity = new ResponseEntity(groupDTOS, HttpStatus.OK);

        expect(restTemplateMock.getForEntity(anyString(), anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);

        Collection<GroupDTO> groupDTOS1 = groupservice.getallGroupsDTO();
        Assert.assertNotNull(groupDTOS1);
        Assert.assertTrue(groupDTOS1.size() == 2);
    }

    /**
     * getallGroupsDTOlite mock test
     */
    @Test
    public void getallGroupsDTOlite() {

        Collection<GroupDTOlite> groupDTOlites = Arrays.asList(groupDTOlite, groupDTOlite2);
        ResponseEntity responseEntity = new ResponseEntity(groupDTOlites, HttpStatus.OK);
        EasyMock.expect(restTemplateMock.getForEntity(anyString(), anyObject())).andReturn(responseEntity);
        EasyMock.replay(restTemplateMock);

        Collection<GroupDTOlite> groupDTOS1 = groupservice.getallGroupsDTOlite();
        Assert.assertNotNull(groupDTOS1);
        Assert.assertTrue(groupDTOS1.size() == 2);
    }

    /**
     * getGroupById mock test
     */
    @Test
    public void getGroupById() {
        ResponseEntity responseEntity = new ResponseEntity(group, HttpStatus.FOUND);
        EasyMock.expect(restTemplateMock.getForEntity(anyString(), anyObject())).andReturn(responseEntity);
        EasyMock.replay(restTemplateMock);

        Group group1 = groupservice.getGroupById(ID);
        Assert.assertEquals(group, group1);

    }

    /**
     * addGroup mock test
     */
    @Test
    public void addGroup() {
        ResponseEntity responseEntity = new ResponseEntity(group, HttpStatus.CREATED);
        EasyMock.expect(restTemplateMock.postForEntity(anyString(), anyObject(), anyObject())).andReturn(responseEntity);
        EasyMock.replay(restTemplateMock);

        Group group1 = groupservice.addGroup(group);
        Assert.assertEquals(group1.getFullName(), group.getFullName());
        verify(restTemplateMock);

    }

    /**
     * updateGroup mock test
     */
    @Test
    public void updateGroup() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        EasyMock.expect(restTemplateMock.postForEntity(anyString(), anyObject(), anyObject())).andReturn(responseEntity);
        replay(restTemplateMock);
        groupservice.updateGroup(group);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * removeGroup mock test
     */
    @Test
    public void removeGroup() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.FOUND);
        restTemplateMock.delete(anyString());
        EasyMock.replay(restTemplateMock);

        groupservice.removeGroup(ID);

    }
}