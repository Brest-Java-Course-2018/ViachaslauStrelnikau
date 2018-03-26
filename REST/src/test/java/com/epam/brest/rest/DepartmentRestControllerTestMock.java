package com.epam.brest.rest;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import javafx.application.Application;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentRestControllerTestMock {

    private static  DepartmentAVGsalary DTO1;
    private static  DepartmentAVGsalary DTO2;
    private static Department department;
    private static final int ID=5;


    private static final int DEPARTMENTID = 1;
    private static final String DEPARTMENTNAME = "Java";
    private static final int AVGSALARY = 1000;
    private static final String DESCRIPTION = "Java department";

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private  DepartmentService MockdepartmentService;

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

        EasyMock.reset(MockdepartmentService);
    }

    @After
    public void teardown()
    {
        EasyMock.verify(MockdepartmentService);
     //   EasyMock.verify(departmentService);

    }

        @Test
    public void getDepartments() throws Exception {

      EasyMock.expect(MockdepartmentService.getDepartmentsAVGSalary())
              .andReturn(Arrays.asList(DTO1,DTO2));
      EasyMock.replay(MockdepartmentService);

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

        EasyMock.expect(MockdepartmentService.getDepartmentById(ID) )
                .andReturn(department);
        EasyMock.replay(MockdepartmentService);

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
    @Test
    public void removeDepartmentById() throws Exception {

        MockdepartmentService.removeDepartmentById(ID);
        EasyMock.replay(MockdepartmentService);

        mockMvc.perform(
                delete("/departments/{id}",ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound ());
    }
//    @Test
//    public void updateDepartmentById() throws Exception {
//
//        Department department = new Department(
//                DEPARTMENTNAME, DESCRIPTION);
//        department.setDepartmentId(DEPARTMENTID);
//
//        MockdepartmentService.updateDepartment(department);
//        EasyMock.expectLastCall();
//
//        EasyMock.replay(MockdepartmentService);
//
//        mockMvc.perform(
//                post("/departments/"+DEPARTMENTID)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"departmentId\":1," +
//                                "\"departmentName\":\"Java\"," +
//                                "\"description\":\"Java department\"}")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print()).andExpect(status().isOk());
//    }
//    @Test
//    public void addDepartmentTest() throws Exception {
//        Department department1 = new Department();
//        department1.setDepartmentId(ID);
//        department1.setDepartmentName("Test");
//        department1.setDescription("test description");
//
//        EasyMock.expect(MockdepartmentService.addDepartment(new Department("1","1"))).andReturn(department1);
//        EasyMock.replay(MockdepartmentService);
//
//        mockMvc.perform(
//                post("/departments")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"departmentName\":\"1\"," +
//                                "\"description\":\"1\"}")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isCreated ())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("departmentId", Matchers.is(ID)))
//                .andExpect(jsonPath("departmentName", Matchers.is("1")))
//                .andExpect(jsonPath("description", Matchers.is("1")));
//    }

//    @Test
//    public void addDepartmentTest() throws Exception {
//        Department department1 = new Department(
//                DEPARTMENTNAME, DESCRIPTION);
//        department1.setDepartmentId(DEPARTMENTID);
//
//        EasyMock.expect(MockdepartmentService.addDepartment(new Department(
//                DEPARTMENTNAME, DESCRIPTION))).andReturn(department1);
//        EasyMock.replay(MockdepartmentService);
//
//        mockMvc.perform(
//                post("/departments")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"departmentName\":\"Java\"," +
//                                "\"description\":\"Java department\"}")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isCreated ())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("departmentId", Matchers.is(1)))
//                .andExpect(jsonPath("departmentName", Matchers.is("Java")))
//                .andExpect(jsonPath("description", Matchers.is("Java department")));
//
//
//        EasyMock.verify(MockdepartmentService);
//    }
//    @Test
//    public void updateDepartment() throws Exception {
//
//        departmentService.updateDepartment(department);
//        EasyMock.expectLastCall();
//        EasyMock.replay(departmentService);
//
////        mockMvc.perform(
////                post("/departments/{id}",department.getDepartmentId())
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(String.valueOf(jsonPath("departmentId", Matchers.is(5))))
//                        .content(String.valueOf(jsonPath("departmentName", Matchers.is("Test"))))
//                        .content(String.valueOf(jsonPath("description", Matchers.is("test description")))))
////                .andDo(print()).andExpect(status().isOk());;
//
//
//        mockMvc.perform(
//                post("/departments/5")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"departmentId\":5," +
//                                "\"departmentName\":\"Test\"," +
//                                "\"description\":\"test description\"}")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print()).andExpect(status().isOk());
//        EasyMock.verify(departmentService);
////        ).andDo(print())
////                .andExpect(status().isOk ())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
////                .andExpect(jsonPath("departmentId", Matchers.is(5)))
////                .andExpect(jsonPath("departmentName", Matchers.is("Test")))
////                .andExpect(jsonPath("description", Matchers.is("test description")));
//    }
}