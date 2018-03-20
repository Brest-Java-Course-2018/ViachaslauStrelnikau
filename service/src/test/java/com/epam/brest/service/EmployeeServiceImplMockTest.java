package com.epam.brest.service;

import com.epam.brest.dao.EmployeeDao;
import com.epam.brest.model.Employee;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class EmployeeServiceImplMockTest {

    private static final Employee EMPLOYEE= new Employee("Test","Test@test.test",1000,1);
    private static final int ID=1;
    @Autowired
    EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;
    /**
     * Testing riseAllSallerysByPercent() method of EmployeeService.
     */
    @Test
    public void riseAllSallerysByPercentTest() {
        List<Employee> employees= new ArrayList<>();
        employees.add(new Employee("Vasia Pupkin","VasiaPupkin@mail.ru",1000,1));
        employees.add(new Employee("Petis Petin","PetisPetin@mail.ru",2000,1));
        employees.add(new Employee("Kolia Kolin","KoliaKolin@mail.ru",3000,1));

        EasyMock.expect(mockEmployeeDao.getEmployees()).andReturn(employees);

        Capture<Employee> captureArgument = Capture.newInstance();

        mockEmployeeDao.updateEmployee(EasyMock.capture(captureArgument));
        EasyMock.expectLastCall().times(3);
        EasyMock.replay(mockEmployeeDao);

        employeeService.riseAllSallerysByPercent(10);
        EasyMock.verify(mockEmployeeDao);
        Assert.assertEquals(1100,employees.get(0).getEmployeeSalary());
        Assert.assertEquals(2200,employees.get(1).getEmployeeSalary());
        Assert.assertEquals(3300,employees.get(2).getEmployeeSalary());
    }
    /**
     * Testing addDepartmentTest() method of EmployeeService.
     */
    @Test
    public void addEmployeeTest()
    {
        EasyMock.expect(mockEmployeeDao.addEmployee(EMPLOYEE)).andReturn(EMPLOYEE);
        EasyMock.replay(mockEmployeeDao);
        employeeService.addEmployee(EMPLOYEE);
        EasyMock.verify(mockEmployeeDao);
    }
    /**
     * Testing updateEmployee() method of EmployeeService.
     */
    @Test
    public void updateEmployeeTest()
    {
        mockEmployeeDao.updateEmployee(EMPLOYEE);
        EasyMock.replay(mockEmployeeDao);
        employeeService.updateEmployee(EMPLOYEE);
        EasyMock.verify(mockEmployeeDao);
    }
    /**
     * Testing GetEmployeeById() method of EmployeeService.
     */
    @Test
    public void GetEmployeeById()
    {
        EasyMock.expect(mockEmployeeDao.getEmployeeById(ID)).andReturn(EMPLOYEE);
        EasyMock.replay(mockEmployeeDao);
        employeeService.getEmployeeById(ID);
        EasyMock.verify(mockEmployeeDao);
    }
}