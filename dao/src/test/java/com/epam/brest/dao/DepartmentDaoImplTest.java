package com.epam.brest.dao;

import com.epam.brest.model.Department;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml",
        "classpath:dao.xml"})
@Rollback
@Transactional
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
        Collection<Department> departments = departmentDAO.getDepartments();
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
     * Testing addDepartment() method of DepartmentDao.
     */
    @Test
    public void addDepartment() {

        Collection<Department> departments =departmentDAO.getDepartments();
        int size_before=departments.size();

        Department obj = new Department("Educational","Educate");
        Department res= departmentDAO.addDepartment(obj);
        Assert.assertNotNull(res);
        Assert.assertTrue(size_before< departmentDAO.getDepartments().size());
        Assert.assertTrue((size_before+1)== departmentDAO.getDepartments().size());
        Assert.assertTrue(res.getDepartmentName().equals(obj.getDepartmentName()));
        Assert.assertTrue(res.getDescription ().equals(obj.getDescription()));
    }
    /**
     * Testing addDepartment() method of DepartmentDao.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addDepartment2()
    {
        Department obj = new Department("Educational","Educate");
        departmentDAO.addDepartment(obj);
        departmentDAO.addDepartment(obj);
    }
    /**
     * Testing addDepartment() method of DepartmentDao.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void addDepartmentwithRule()
    {
        Department obj = new Department("Educational","Educate");
        departmentDAO.addDepartment(obj);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Record is in base");
        departmentDAO.addDepartment(obj);
    }

    /**
     * Testing updateDepartment() method of DepartmentDao.
     */
    @Test
    public void updateDepartment() {
        Department obj = new Department("Educational","Educate something");
        Department newDepartment= departmentDAO.addDepartment(obj);
        newDepartment.setDepartmentName("Educational_new");
        departmentDAO.updateDepartment(newDepartment);
        Department updDepartment=departmentDAO.getDepartmentById(newDepartment.getDepartmentId());

        Assert.assertTrue(newDepartment.getDepartmentId()==updDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(updDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(updDepartment.getDescription()));
    }

    /**
     * Testing removeDepartmentById() method of DepartmentDao.
     */
    @Test
    public void removeDepartment() {

        Department obj = new Department("EducationalDelete","EducationalDelete");

        departmentDAO.addDepartment(obj);
        Collection<Department> departments =departmentDAO.getDepartments();
        int size_before=departments.size();
        departmentDAO.removeDepartmentById(obj.getDepartmentId());
        Assert.assertTrue((size_before-1)== departmentDAO.getDepartments().size());
    }
}