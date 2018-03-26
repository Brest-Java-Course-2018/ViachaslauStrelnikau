package com.epam.brest.rest;

import com.epam.brest.model.Department;
import com.epam.brest.service.DepartmentService;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:dao.xml", "classpath:servicetest.xml"})
@Rollback
@Transactional
public class DepartmentRestControllerTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRestController departmentRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();

    }
    @Test
    public void addDepartmentTest() throws Exception {
        Department department = new Department("test","test");

        mockMvc.perform(
                post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\":\"test\"," +
                                "\"description\":\"test\"}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated ())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("departmentId", Matchers.not (0)))
                .andExpect(jsonPath("departmentName", Matchers.is("test")))
                .andExpect(jsonPath("description", Matchers.is("test")));
    }

}
