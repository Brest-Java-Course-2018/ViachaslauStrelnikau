package com.epam.brest.web_app.controllers;

import com.epam.brest.client_rest.DepartmentConsumerRest;
import com.epam.brest.model.DepartmentAVGsalary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-webapp-context.xml"})
public class DepartmentControllerTest {
    private static  DepartmentAVGsalary DTO1;
    private static DepartmentAVGsalary DTO2;
    @Autowired
    DepartmentConsumerRest MockdepartmentService;

    @Autowired
    DepartmentController departmentController;
    private MockMvc mockMvcMvc;

//    @Before
//    public void testSetUp()
//    {
//        mockMvcMvc= MockMvcBuilders.standaloneSetup(departmentController)
//                .setMessageConverters(new MappingJackson2HttpMessageConverter())
//                .build();
//        DTO1 = new DepartmentAVGsalary();
//        DTO2 = new DepartmentAVGsalary();
//
//        DTO1.setDepartmentId(1);
//        DTO1.setDepartmentName("Name1");
//        DTO1.setAvgSalary(1000);
//        DTO2.setDepartmentId(2);
//        DTO2.setDepartmentName("Name2");
//        DTO2.setAvgSalary(2000);
//    }

    @Test
    public void getAllDepartments() throws Exception {
//        EasyMock.expect(MockdepartmentService.getDepartmentsAVGSalary()).andReturn(Arrays.asList(DTO1,DTO2));
//        EasyMock.replay(MockdepartmentService);
//
//        mockMvcMvc.perform(
//                get("/departments")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
//                .andExpect(jsonPath("$[0].departmentName", Matchers.is("Name1")))
//                .andExpect(jsonPath("$[0].avgSalary", Matchers.is(1000)));
//
    }
}