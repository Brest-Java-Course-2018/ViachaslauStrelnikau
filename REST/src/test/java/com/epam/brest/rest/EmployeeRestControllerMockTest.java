package com.epam.brest.rest;

import com.epam.brest.model.Employee;
import com.epam.brest.service.EmployeeService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class EmployeeRestControllerMockTest {

    private static Employee employee1;
    private static  Employee employee2;

    @Autowired
    private EmployeeRestController employeeRestController ;

    @Autowired
    private EmployeeService mockdepartmentService;

    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();

        employee1 = new Employee();
        employee2 = new Employee();

        employee1.setDepartmentId(1);
        employee1.setEmployeeName("Name1");
        employee1.setEmployeeSalary(1000);
        employee1.setEmployeeEmail("email1@email.email");
        employee1.setEmployeeId(1);

        employee2.setDepartmentId(1);
        employee2.setEmployeeName("Name2");
        employee2.setEmployeeSalary(2000);
        employee2.setEmployeeEmail("email2@email.email");
        employee2.setEmployeeId(2);

        EasyMock.reset(mockdepartmentService);
    }

    @After
    public void teardown()
    {
        EasyMock.verify(mockdepartmentService);

    }

    @Test
    public void getEmployee() throws Exception {

        EasyMock.expect(mockdepartmentService.getEmployees())
                .andReturn(Arrays.asList(employee1,employee2)).times(1);
        EasyMock.replay(mockdepartmentService);

        mockMvc.perform(
                get("/employees")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].employeeName", Matchers.is("Name1")))
                .andExpect(jsonPath("$[0].employeeEmail", Matchers.is("email1@email.email")))
                .andExpect(jsonPath("$[0].employeeSalary", Matchers.is(1000)))
                .andExpect(jsonPath("$[0].employeeId", Matchers.is(1)));
    }
    @Test
    public void addEmployee() throws Exception {
        EasyMock.expect(mockdepartmentService.addEmployee(EasyMock.anyObject())).andReturn(employee1);
        EasyMock.replay(mockdepartmentService);

        Gson gson = new Gson();

        mockMvc.perform(post("/employees",1)
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(employee1))
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isCreated());
    }
}
