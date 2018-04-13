package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.model.Group;
import com.epam.brest.service.GroupService;
import com.google.gson.Gson;
import org.easymock.EasyMock;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.anyObject;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-webapp-test.xml")
public class GroupControllerTest {

    @Autowired
    GroupService groupCounsumerRestMock;

    @Autowired
    GroupController groupController;

    private MockMvc mockMvc;

    private static final int ID=1;
    private static GroupDTO groupDTO;
    private static GroupDTO groupDTO2;
    private static Group group;
    private static Group group2;
    private static Group group_empty;

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

        group_empty= new Group();
    }
    @After
    public void testClear()
    {
//        EasyMock.verify(groupCounsumerRestMock);
//        EasyMock.reset(groupCounsumerRestMock);
    }
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

    @Test
    public void updateGroup() throws Exception {
        groupCounsumerRestMock.updateGroup(anyObject());
        EasyMock.replay(groupCounsumerRestMock);
        Gson gson = new Gson();
        mockMvc.perform(
                post("/groups/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(group))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/groups"));
        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }

    @Test
    public void newGroup() throws Exception {
        mockMvc.perform(get("/addGroup/"))
                .andExpect(status().isOk())
                .andExpect(view().name("editgroups"))
                .andExpect(model().attribute("isNew",true ))
                .andExpect(model().attribute("group",group_empty ));
    }

    @Test
    public void addGroup() throws Exception {
        EasyMock.expect(groupCounsumerRestMock.addGroup(anyObject())).andReturn(group);
        EasyMock.replay(groupCounsumerRestMock);

        Gson gson = new Gson();
        mockMvc.perform(
                post("/addGroup")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(group2))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isFound());

        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }
//
//    @Test
//    public void deleteGroup() {
//    }
}