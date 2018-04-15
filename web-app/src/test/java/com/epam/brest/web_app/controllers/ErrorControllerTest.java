package com.epam.brest.web_app.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.easymock.EasyMock.anyString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-webapp-test.xml")

public class ErrorControllerTest {

    @Autowired
    ErrorController errorController;

    @Autowired
    ExceptionThrowingController exceptionThrowingController;

    private MockMvc mockMvc;

    @Before
    public void testSetUp() {
        mockMvc = MockMvcBuilders.standaloneSetup (exceptionThrowingController)
                .setControllerAdvice(errorController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test

    public void testHandleException() throws Exception {

        mockMvc.perform(get("/exceptionTrow"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("exception"))
                .andExpect(model().attribute("Title","Exception occured" ))
                .andExpect(model().attribute("Text","org.springframework.web.client.RestClientException: global_error_test" ));
    }
}