package com.epam.brest.service;

import com.epam.brest.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:dao.xml", "classpath:service-test.xml"})
public class DepartmentServiceImplTest {

    private static final int ID=1;
    private static final String description="Academic department";
    @Autowired
    private  DepartmentService departmentService;
    @Test
    public void updateDepartmentDescription() {
        departmentService.updateDepartmentDescription(ID,description);

        Department department= departmentService.getDepartmentById(ID);
        Assert.assertTrue(department.getDescription().equals(description));
    }
}