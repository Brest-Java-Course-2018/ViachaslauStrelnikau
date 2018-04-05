package com.epam.brest.rest;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class GroupRestControllerTest tests GroupRestController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest-mock-test.xml")
public class GroupRestControllerTest {

    @Autowired
    GroupRestController groupRestController;

    @Autowired
    GroupService groupServiceMock;

    private static Group group;
    private static Group group2;

    private static GroupDTO groupDTO;
    private static GroupDTO groupDTO2;

    private static GroupDTOlite groupDTOlite;
    private static GroupDTOlite groupDTOlite2;
    private static final int ID = 11;

    private MockMvc mockMvc;

    /**
     * Test set up.
     */
    @Before
    public void TestSetUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(groupRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(groupServiceMock);

        group = new Group();
        group.setGroupId(11);
        group.setFullName("Test1");
        group.setShortName("T1");
        group.setDescription("Test");

        group2 = new Group();
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
     * Test End.
     */
    @After
    public void TestClear() {
        EasyMock.verify(groupServiceMock);
    }

    /**
     * Test get mapping of request "/students".
     */
    @Test
    public void getGroupsDTOrest() throws Exception {
        EasyMock.expect(groupServiceMock.getallGroupsDTO())
                .andReturn(Arrays.asList(groupDTO, groupDTO2));
        EasyMock.replay(groupServiceMock);

        mockMvc.perform(get("/groups")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].shortName", Matchers.is("T1")))
                .andExpect(jsonPath("$[0].fullName", Matchers.is("Test1")))
                .andExpect(jsonPath("$[0].groupAvgMarks", Matchers.is(1.1)))
                .andExpect(jsonPath("$[1].shortName", Matchers.is("T2")))
                .andExpect(jsonPath("$[1].fullName", Matchers.is("Test2")))
                .andExpect(jsonPath("$[1].groupAvgMarks", Matchers.is(2.2)));
    }

    /**
     * Test get mapping of request "/groupsdto".
     */
    @Test
    public void getallGroupsDTOliterest() throws Exception {
        EasyMock.expect(groupServiceMock.getallGroupsDTOlite())
                .andReturn(Arrays.asList(groupDTOlite, groupDTOlite2));
        EasyMock.replay(groupServiceMock);

        mockMvc.perform(get("/groupsdto")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].fullName", Matchers.is("Test1")))
                .andExpect(jsonPath("$[1].fullName", Matchers.is("Test2")));
    }

    /**
     * Test get mapping of request "/groups/{id}".
     */
    @Test
    public void getGroupByIdrest() throws Exception {
        EasyMock.expect(groupServiceMock.getGroupById(ID)).andReturn(group);
        EasyMock.replay(groupServiceMock);
        mockMvc.perform(get("/groups/{id}", ID)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("groupId", Matchers.is(11)))
                .andExpect(jsonPath("shortName", Matchers.is("T1")))
                .andExpect(jsonPath("fullName", Matchers.is("Test1")))
                .andExpect(jsonPath("description", Matchers.is("Test")));
    }

    /**
     * Test post mapping of request "/groups".
     */
    @Test
    public void addGrouprest() throws Exception {
        EasyMock.expect(groupServiceMock.addGroup(group2)).andReturn(group);
        EasyMock.replay(groupServiceMock);

        Gson gson = new Gson();
        mockMvc.perform(
                post("/groups")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(group2))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("groupId", Matchers.is(11)))
                .andExpect(jsonPath("shortName", Matchers.is("T1")))
                .andExpect(jsonPath("fullName", Matchers.is("Test1")))
                .andExpect(jsonPath("description", Matchers.is("Test")));

    }

    /**
     * Test post mapping of request "/groups/{id}".
     */
    @Test
    public void updateGrouprest() throws Exception {
        groupServiceMock.updateGroup(group);
        EasyMock.replay(groupServiceMock);

        Gson gson = new Gson();
        mockMvc.perform(
                post("/groups/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(gson.toJson(group))
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test delete mapping of request "/groups/{id}".
     */
    @Test
    public void removeGrouprest() throws Exception {
        groupServiceMock.removeGroup(ID);
        EasyMock.replay(groupServiceMock);

        mockMvc.perform(delete("/groups/{id}", ID)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isFound());
    }
}