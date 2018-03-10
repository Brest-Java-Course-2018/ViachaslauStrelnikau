package com.epam.brest.service;

import com.epam.brest.dao.EmployeeDao;
import com.epam.brest.model.Employee;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class EmployeeServiceImplMockTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    @Test
    public void riseAllSallerysByPercent() {
        List<Employee> employees= new ArrayList<>();
        employees.add(new Employee("Vasia Pupkin",1000,1));
        employees.add(new Employee("Petis Petin",2000,1));
        employees.add(new Employee("Kolia Kolin",3000,1));

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
}