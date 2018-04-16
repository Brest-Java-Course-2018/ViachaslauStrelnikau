package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.model.Group;
import com.epam.brest.service.GroupService;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test GroupController class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-webapp-test.xml")
public class GroupControllerTest {

    /**
     * GroupService object injection.
     */
    @Autowired
    GroupService groupCounsumerRestMock;
    /**
     * GroupController object injection.
     */
    @Autowired
    GroupController groupController;

    private MockMvc mockMvc;

    private static final int ID=1;
    private static GroupDTO groupDTO;
    private static GroupDTO groupDTO2;
    private static Group group;
    private static Group group2;
    private static Group group_empty;
    private static Group valid_group;
    /**
     * Test set up.
     */
    @Before
    public void testSetUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(groupController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
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

        group = new Group();
        group.setGroupId(1);
        group.setFullName("Test1");
        group.setShortName("T1");
        group.setDescription("Test");

        group2 = new Group();
        group2.setFullName("Test1");
        group2.setShortName("T1");
        group2.setDescription("Test");

        valid_group = new Group();
        valid_group.setFullName("T");
        valid_group.setShortName("Q2");
        valid_group.setDescription("Z2");

        group_empty= new Group();
    }

    /**
     * Method groups tests groups method of GroupController.
     * @throws Exception exception
     */
    @Test
    public void groups() throws Exception {
        Collection<GroupDTO> groupDTOS=Arrays.asList(groupDTO,groupDTO2);
        EasyMock.expect(groupCounsumerRestMock.getallGroupsDTO()).andReturn(groupDTOS);
        EasyMock.replay(groupCounsumerRestMock);
        mockMvc.perform(get("/groups/"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"))
                .andExpect(model().attribute("groups",groupDTOS ));
        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }

    /**
     * Method editGroup tests editGroup method of GroupController.
     * @throws Exception exception
     */
    @Test
    public void editGroup() throws Exception {

        EasyMock.expect(groupCounsumerRestMock.getGroupById(ID)).andReturn(group);
        EasyMock.replay(groupCounsumerRestMock);
        mockMvc.perform(get("/groups/"+ID))
                .andExpect(status().isOk())
                .andExpect(view().name("editgroups"))
                .andExpect(model().attribute("isNew",false ))
                .andExpect(model().attribute("group",group ));
        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }

    /**
     * Method updateGroup tests updateGroup method of GroupController.
     * @throws Exception exception
     */
    @Test
    public void updateGroup() throws Exception {
        groupCounsumerRestMock.updateGroup(group);
        EasyMock.replay(groupCounsumerRestMock);
        mockMvc.perform(
                post("/groups/{id}", ID)
                        .param("groupId",Integer.toString(group.getGroupId()))
                        .param("shortName",group.getShortName())
                        .param("fullName",group.getFullName())
                        .param("description",group.getDescription())
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/groups"));

        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }
    /**
     * Method newGroup tests newGroup method of GroupController.
     * @throws Exception exception
     */
    @Test
    public void newGroup() throws Exception {
        mockMvc.perform(get("/addGroup/"))
                .andExpect(status().isOk())
                .andExpect(view().name("editgroups"))
                .andExpect(model().attribute("isNew",true ))
                .andExpect(model().attribute("group",group_empty ));
    }

    /**
     * Method addGroup tests addGroup method of GroupController.
     * @throws Exception exception
     */
    @Test
    public void addGroup() throws Exception {
        EasyMock.expect(groupCounsumerRestMock.addGroup(group2)).andReturn(group);
        EasyMock.replay(groupCounsumerRestMock);

        mockMvc.perform(
                post("/addGroup")
                        .param("groupId",Integer.toString(group2.getGroupId()))
                        .param("shortName",group2.getShortName())
                        .param("fullName",group2.getFullName())
                        .param("description",group2.getDescription())
        ).andDo(print())
                .andExpect(status().isFound());

        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }

    /**
     * Method deleteGroup tests deleteGroup method of GroupController.
     * @throws Exception exception
     */
    @Test
    public void deleteGroup() throws Exception {
        groupCounsumerRestMock.removeGroup(ID);
        EasyMock.replay(groupCounsumerRestMock);
        mockMvc.perform(get("/groups/{id}/delete",ID))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/groups"));
        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }

}