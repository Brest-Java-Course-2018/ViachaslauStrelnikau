package com.epam.brest.web_app.controllers;

import com.epam.brest.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.easymock.EasyMock.anyString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-webapp-test.xml")
public class HomeControllerTest {


    @Autowired
    HomeController homeController;

    private MockMvc mockMvc;
    @Before
    public void testSetUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }
    @Test
    public void defaultPageRedirect() throws Exception {

        mockMvc.perform(get("/"+anyString()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:groups"));
    }
}