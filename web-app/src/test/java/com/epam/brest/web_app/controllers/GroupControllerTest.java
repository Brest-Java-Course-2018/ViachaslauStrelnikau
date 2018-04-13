package com.epam.brest.web_app.controllers;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.service.GroupService;
import org.easymock.EasyMock;
import org.junit.After;
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

import static org.easymock.EasyMock.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    }
    @After
    public void testClear()
    {
        EasyMock.verify(groupCounsumerRestMock);
        EasyMock.reset(groupCounsumerRestMock);
    }
    @Test
    public void groups() throws Exception {
        EasyMock.expect(groupCounsumerRestMock.getallGroupsDTO()).andReturn(Arrays.asList(groupDTO,groupDTO2));
        EasyMock.replay(groupCounsumerRestMock);
        mockMvc.perform(get("/groups/"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"));
    }

//    @Test
//    public void editGroup() throws Exception {
//
//        mockMvc.perform(get("/groups/1"+ID))
//                .andExpect(status().isOk())
//                .andExpect(view().name("editgroups"));
//    }
//
//    @Test
//    public void updateGroup() {
//    }
//
//    @Test
//    public void newGroup() {
//    }
//
//    @Test
//    public void addGroup() {
//    }
//
//    @Test
//    public void deleteGroup() {
//    }
}