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
/**
 * class DepartmentDaoImplTest tests DepartmentDao.
 */
public class DepartmentDaoImplTest {
    @Autowired
    DepartmentDao departmentDAO;

    /**
     * Testing getDepartments() method of DepartmentDao.
     */
    @Test
    public void getDepartments() {
        List<Department> departments = departmentDAO.getDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    /**
     * Testing getDepartmentById() method of DepartmentDao.
     */
    @Test
    public void getDepartmentById() {
        Department department = departmentDAO.getDepartmentById(1);
        Assert.assertEquals(1, ((departmentDAO.getDepartmentById(1)).getDepartmentId()));
        Assert.assertTrue(department.getDepartmentName().equals("Destribution"));
        Assert.assertTrue(department.getDescription().equals("Destribute something"));
    }
    /**
     * Testing getDepartmentName() method of DepartmentDao.
     */
    @Test
    public void getDepartmentByName() {
        Department department = departmentDAO.getDepartmentByName("Destribution");
        Assert.assertEquals(1, ((departmentDAO.getDepartmentById(1)).getDepartmentId()));
        Assert.assertTrue(department.getDepartmentName().equals("Destribution"));
        Assert.assertTrue(department.getDescription().equals("Destribute something"));
    }
    /**
     * Testing addDepartment() method of DepartmentDao.
     */
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
    /**
     * Testing updateDepartment() method of DepartmentDao.
     */
    @Test
    public void updateDepartment()
    {
        Department department = departmentDAO.getDepartmentById(1);
        department.setDescririon("Something changed in my desription!");
        department.setDepartmentName("New Department");

        departmentDAO.updateDepartment(department);
        Department department2=departmentDAO.getDepartmentById(department.getDepartmentId());

        Assert.assertTrue(department2.getDescription().equals("Something changed in my desription!"));
        Assert.assertTrue(department2.getDepartmentName().equals("New Department"));

        Department department_2 = departmentDAO.getDepartmentById(2);
        department_2.setDescririon("Something changed in my desription!");
        department_2.setDepartmentName("New Department");

        departmentDAO.updateDepartment(department_2);
        Department department2_2=departmentDAO.getDepartmentById(department_2.getDepartmentId());
        Assert.assertTrue(department2_2.getDescription().equals("Something changed in my desription!"));
        Assert.assertFalse(department2_2.getDepartmentName().equals("New Department"));
    }
    /**
     * Testing removeDepartmentById() method of DepartmentDao.
     */
    @Test
    public void removeDepartment()
    {
        Assert.assertTrue(departmentDAO.removeDepartmentById(1));
        Assert.assertFalse(departmentDAO.removeDepartmentById(1));
    }

}