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
     * Testing getEmployees() method of EmployeeDao.
     */
    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertFalse(employees.isEmpty());
    }
    /**
     * Testing getEmployeeById() method of EmployeeDao.
     */
    @Test
    public void getEmployeeById() {
        Employee employee =  employeeDao.getEmployeeById(1);
        Assert.assertEquals(1,((employeeDao.getEmployeeById(1)).getEmployeeId()));
        Assert.assertTrue(employee.getEmployeeName().equals("Vasia Pupkin"));
        Assert.assertTrue(employee.getEmployeeSalary()==1000);
        Assert.assertTrue(employee.getDepartmentId()==1);
    }
    /**
     * Testing addEmployee() method of EmployeeDao.
     */
    @Test
    public void addEmployee() {
        List<Employee> employees= employeeDao.getEmployees();
        int size_before=employees.size();

        Employee new_Employee= new Employee("Piotr Vasiliev",2000,1);
        Employee res= employeeDao.addEmployee(new_Employee);

        Assert.assertNotNull(res);
        Assert.assertTrue((size_before+1)==employeeDao.getEmployees().size());
        Assert.assertTrue(res.getEmployeeName().equals(new_Employee.getEmployeeName()));
        Assert.assertTrue(res.getEmployeeSalary()==new_Employee.getEmployeeSalary());
        Assert.assertTrue(res.getDepartmentId()==new_Employee.getDepartmentId());
    }
    /**
     * Testing updateDepartment() method of EmployeeDao.
     */
    @Test
    public void updateEmployee() {
        Employee obj =new Employee("Semen Semenov",2000,1);
        Employee new_Employee=employeeDao.addEmployee(obj);
        new_Employee.setEmployeeName("Semen Petrov");
        new_Employee.setEmployeeSalary(1000);

        employeeDao.updateEmployee(new_Employee);
        Employee upd_Employee=employeeDao.getEmployeeById(new_Employee.getEmployeeId());

        Assert.assertTrue(new_Employee.getDepartmentId()==upd_Employee.getDepartmentId());
        Assert.assertTrue(new_Employee.getEmployeeSalary()==upd_Employee.getEmployeeSalary());
        Assert.assertTrue(new_Employee.getEmployeeId()==upd_Employee.getEmployeeId());
        Assert.assertTrue(new_Employee.getEmployeeName().equals(upd_Employee.getEmployeeName()));
    }
    /**
     * Testing removeEmployeeById() method of EmployeeDao.
     */
    @Test
    public void removeEmployeeById() {
        Employee employee=new Employee("Ivanov Ivan",500,1);
        employeeDao.addEmployee(employee);
        List<Employee> list_emp=employeeDao.getEmployees();
        int size_before=list_emp.size();
        employeeDao.removeEmployeeById(employee.getEmployeeId());
        Assert.assertEquals((size_before-1),employeeDao.getEmployees().size());
    }
}