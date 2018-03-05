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

import java.util.List;

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
    public void addDepartment() {

        List<Department> departments =departmentDAO.getDepartments();
        int size_before=departments.size();

        Department obj = new Department("Educational","Educate");
        Department res= departmentDAO.addDepartment(obj);
        Assert.assertNotNull(res);
        Assert.assertTrue(size_before< departmentDAO.getDepartments().size());
        Assert.assertTrue((size_before+1)== departmentDAO.getDepartments().size());
        Assert.assertTrue(res.getDepartmentName().equals(obj.getDepartmentName()));
        Assert.assertTrue(res.getDescription ().equals(obj.getDescription()));



//        //test1
//        Department newDepartmebt = new Department();
//        newDepartmebt.setDepartmentName("Testing");
//        newDepartmebt.setDescririon("Tests everything");
//
//        Department department = departmentDAO.addDepartment(newDepartmebt);
//        Assert.assertTrue(newDepartmebt.getDepartmentName().equals(department.getDepartmentName()));
//        Assert.assertTrue(newDepartmebt.getDescription().equals(department.getDescription()));
//        //test2
//        Department newDepartmebt2 = new Department();
//        newDepartmebt.setDepartmentName("Testing");
//        newDepartmebt.setDescririon("Tests everything");
//
//        Department department2 = departmentDAO.addDepartment(newDepartmebt);
//        Assert.assertNull(department2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addDepartment2()
    {
        Department obj = new Department("Educational","Educate");
        departmentDAO.addDepartment(obj);
        departmentDAO.addDepartment(obj);

    }

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

//        //test 1
//        Department department = new Department();
//        department.setDepartmentId(1);
//        department.setDescririon("Something changed in my desription!");
//        department.setDepartmentName("New Department");
//
//        departmentDAO.updateDepartment(department);
//        Department department2 = departmentDAO.getDepartmentById(department.getDepartmentId());
//
//        Assert.assertTrue(department2.getDescription().equals("Something changed in my desription!"));
//        Assert.assertTrue(department2.getDepartmentName().equals("New Department"));
//
//        //test 2
//        Department department_2 = new Department();
//        department_2.setDepartmentId(2);
//        department_2.setDescririon("Something changed in my desription!");
//        department_2.setDepartmentName("HR");
//        departmentDAO.updateDepartment(department_2);
//
//        Department department2_2 = departmentDAO.getDepartmentById(department_2.getDepartmentId());
//        Assert.assertTrue(department2_2.getDescription().equals("Something changed in my desription!"));
//        Assert.assertFalse(department2_2.getDepartmentName().equals("HR"));
    }

    /**
     * Testing removeDepartmentById() method of DepartmentDao.
     */
    @Test
    public void removeDepartment() {

        Department obj = new Department("EducationalDelete","EducationalDelete");

        departmentDAO.addDepartment(obj);
        List<Department> departments =departmentDAO.getDepartments();
        int size_before=departments.size();
        departmentDAO.removeDepartmentById(obj.getDepartmentId());
        Assert.assertTrue((size_before-1)== departmentDAO.getDepartments().size());

//        Assert.assertTrue(departmentDAO.removeDepartmentById(3));
//        Assert.assertFalse(departmentDAO.removeDepartmentById(3));
    }

}