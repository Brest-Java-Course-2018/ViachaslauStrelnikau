package com.epam.brest.rest;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentRestControllerTest {

    private static  DepartmentAVGsalary DTO1;
    private static  DepartmentAVGsalary DTO2;
    private static Department department;
    private static final int ID=5;

    @Autowired
    private DepartmentRestController departmentRestController;
    @Autowired
    private  DepartmentService departmentService;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        DTO1 = new DepartmentAVGsalary();
        DTO2 = new DepartmentAVGsalary();
        department =new Department();
        DTO1.setDepartmentId(1);
        DTO1.setDepartmentName("Name1");
        DTO1.setAvgSalary(1000);
        DTO2.setDepartmentId(2);
        DTO2.setDepartmentName("Name2");
        DTO2.setAvgSalary(2000);
        department.setDepartmentId(5);
        department.setDepartmentName("Test");
        department.setDescription("test description");
    }

    @After
    public void teardown()
    {
        EasyMock.verify(departmentService);
        EasyMock.verify(departmentService);
        EasyMock.reset(departmentService);
    }

        @Test
    public void getDepartments() throws Exception {

      EasyMock.expect(departmentService.getDepartmentsAVGSalary())
              .andReturn(Arrays.asList(DTO1,DTO2));
      EasyMock.replay(departmentService);

      mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
              .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
              .andExpect(jsonPath("$[0].departmentName", Matchers.is("Name1")))
              .andExpect(jsonPath("$[0].avgSalary", Matchers.is(1000)));
    }

    @Test
    public void getDepartmentById() throws Exception {

        EasyMock.expect(departmentService.getDepartmentById(ID) )
                .andReturn(department);
        EasyMock.replay(departmentService);

        mockMvc.perform(
                get("/departments/{id}",ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound ())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("departmentId", Matchers.is(5)))
                .andExpect(jsonPath("departmentName", Matchers.is("Test")))
                .andExpect(jsonPath("description", Matchers.is("test description")));
    }
}