package com.epam.brest.web_app.controllers;

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

    @Before
    public void testSetUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(groupController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }
    @After
    public void testClear()
    {

    }
    @Test
    public void groups() throws Exception {
        mockMvc.perform(get("/groups/"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"));
    }

    @Test
    public void editGroup() throws Exception {

//        mockMvc.perform(get("/groups/1"+ID))
//                .andExpect(status().isOk())
//                .andExpect(view().name("editgroups"));
    }

    @Test
    public void updateGroup() {
    }

    @Test
    public void newGroup() {
    }

    @Test
    public void addGroup() {
    }

    @Test
    public void deleteGroup() {
    }
}