package com.epam.brest.service;

import com.epam.brest.dao.DepartmentDaoImpl;
import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:dao.xml", "classpath:service-test.xml"})
public class DepartmentServiceImplTest {

    private static final int ID=1;
    private static final String description="Academic department";
    @Autowired
    private  DepartmentService departmentService;
    /**
     * Testing getDepartmentById() method of DepartmentService.
     */
    @Test
    public void updateDepartmentDescription() {
        departmentService.updateDepartmentDescription(ID,description);
        Department department= departmentService.getDepartmentById(ID);
        Assert.assertTrue(department.getDescription().equals(description));
    }
    /**
     * Testing getDepartmentsAVGSalary() method of DepartmentService.
     */
    @Test
    public void getAvgDepartmentsSalary() {
        Collection<DepartmentAVGsalary> departmentsAVGSalary = departmentService.getDepartmentsAVGSalary();
        Assert.assertFalse(departmentsAVGSalary.isEmpty());
    }
    /**
     * Testing addDepartment() method of departmentService.
     */
    @Test
    public void addDepartment() {

        Department department=new Department();
        department.setDepartmentId(1000);
        department.setDepartmentName("Test");
        department.setDescription("TestDescription");
        Department department1=departmentService.addDepartment(department);

        Assert.assertTrue(department1.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(department1.getDescription ().equals(department.getDescription()));
    }

}