package com.epam.brest.client_rest;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import com.epam.brest.service.DepartmentService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class DepartmentConsumerRestTest {

  private static DepartmentAVGsalary DTO1;
    private static DepartmentAVGsalary  DTO2;
    private static Department department;
    @Autowired
    DepartmentService depardtmentService;
    @Autowired
    RestTemplate RestTemplateMock;
    @Before
    public void setUp() {
        DTO1 = new DepartmentAVGsalary();
        DTO2 = new DepartmentAVGsalary();
        DTO1.setDepartmentId(1);
        DTO1.setDepartmentName("Name1");
        DTO1.setAvgSalary(1000);
        DTO2.setDepartmentId(2);
        DTO2.setDepartmentName("Name2");
        DTO2.setAvgSalary(2000);
        department= new Department();
        department.setDepartmentId(3);
        department.setDepartmentName("Test3");
        department.setDescription("Info");
    }
    @After
    public void tearDown()
    {
        verify(RestTemplateMock);
        reset(RestTemplateMock);
    }
    @Test
    public void getAllDepartments()
    {
        List<DepartmentAVGsalary> departmentAVGsalaries= Arrays.asList(DTO1,DTO2);
        ResponseEntity responseEntity =new ResponseEntity(departmentAVGsalaries, HttpStatus.OK);

       EasyMock.expect(RestTemplateMock.getForEntity(anyString(),anyObject())).andReturn(responseEntity);
       replay(RestTemplateMock);

        Collection<DepartmentAVGsalary> departmentAVG= depardtmentService.getDepartmentsAVGSalary();

        Assert.assertNotNull(departmentAVG);
        Assert.assertEquals(2,departmentAVG.size());
    }
    @Test
    public void getDepartmentByID()
    {

        ResponseEntity responseEntity =new ResponseEntity(department, HttpStatus.FOUND);
        EasyMock.expect(RestTemplateMock.getForEntity(anyString(),anyObject())).andReturn(responseEntity);
        replay(RestTemplateMock);

        Department  result= depardtmentService.getDepartmentById(3);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getDepartmentName(),"Test3");

    }
    @Test
    public void addDepartment()
    {
        ResponseEntity responseEntity =new ResponseEntity(department, HttpStatus.FOUND);
        EasyMock.expect(RestTemplateMock.postForEntity(anyString(),anyObject(),anyObject())).andReturn(responseEntity);
        replay(RestTemplateMock);
        Department  result= depardtmentService.addDepartment(department);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getDepartmentId(),3);
    }

}