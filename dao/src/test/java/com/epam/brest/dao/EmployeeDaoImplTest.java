package com.epam.brest.dao;

import com.epam.brest.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml",
        "classpath:dao.xml"})
@Rollback
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDao employeeDao;
    /**
     * Testing employeeDao() method of EmployeeDao.
     */
    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {
        Employee employee =  employeeDao.getEmployeeById(1);
        Assert.assertEquals(1,((employeeDao.getEmployeeById(1)).getEmployeeId()));
        Assert.assertTrue(employee.getEmployeeName().equals("Vasia Pupkin"));
        Assert.assertTrue(employee.getEmployeeSalary()==1000);
        Assert.assertTrue(employee.getDepartmenyId()==1);
    }

    @Test
    public void addEmployee() {
    }

    @Test
    public void updateEmployee() {
    }

    @Test
    public void removeEmployeeById() {
    }
}