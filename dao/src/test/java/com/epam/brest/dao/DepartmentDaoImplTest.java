package com.epam.brest.dao;

import com.epam.brest.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml"})

public class DepartmentDaoImplTest {
    @Autowired
    DepartmentDao departmentDAO;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDAO.getDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDAO.getDepartmentById(1);
        Assert.assertEquals(1, ((departmentDAO.getDepartmentById(1)).getDepartmentId()));
        Assert.assertTrue(department.getDepartmentName().equals("Destribution"));
        Assert.assertTrue(department.getDescription().equals("Destribute something"));
    }
    @Test
    public void getDepartmentByName() {
        Department department = departmentDAO.getDepartmentByName("Destribution");
        Assert.assertEquals(1, ((departmentDAO.getDepartmentById(1)).getDepartmentId()));
        Assert.assertTrue(department.getDepartmentName().equals("Destribution"));
        Assert.assertTrue(department.getDescription().equals("Destribute something"));
    }
    @Test
    public void addDepartment()
    {
        Department newDepartmebt = new Department();
        newDepartmebt.setDepartmentName("Testing");
        newDepartmebt.setDescririon("Tests everything");

        Department department = departmentDAO.addDepartment(newDepartmebt);
        Assert.assertTrue(newDepartmebt.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(newDepartmebt.getDescription().equals(department.getDescription()));

        Department newDepartmebt2 = new Department();
        newDepartmebt.setDepartmentName("Testing");
        newDepartmebt.setDescririon("Tests everything");
        Department department2 = departmentDAO.addDepartment(newDepartmebt);
        Assert.assertNull(department2);
    }
}