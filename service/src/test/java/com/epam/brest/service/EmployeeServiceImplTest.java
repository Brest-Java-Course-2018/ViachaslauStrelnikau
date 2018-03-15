package com.epam.brest.service;

import com.epam.brest.dao.EmployeeDao;
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
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:dao.xml", "classpath:service-test.xml"})
@Rollback
@Transactional
public class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;

    /**
     * Testing riseAllSallerysByPercent() method of EmployeeService.
     */
    @Test
    public void riseAllSallerysByPercent() {
        List<Employee> employees_before =employeeService.getEmployees();
        employeeService.riseAllSallerysByPercent(10);
        List<Employee> employees_after =employeeService.getEmployees();

        Assert.assertTrue (employees_before.size()==employees_after.size());
        for(int count=0; count<employees_before.size();count++)
        {
            Assert.assertTrue((employees_before.get(count).getEmployeeSalary()+employees_before.get(count).getEmployeeSalary()*0.1)==employees_after.get(count).getEmployeeSalary());
            Assert.assertEquals(employees_before.get(count).getEmployeeName(),employees_after.get(count).getEmployeeName());
            Assert.assertEquals(employees_before.get(count).getEmployeeId(),employees_after.get(count).getEmployeeId());
            Assert.assertEquals(employees_before.get(count).getDepartmentId(),employees_after.get(count).getDepartmentId());
        }
    }
}