package com.epam.brest.service;

import com.epam.brest.dao.EmployeeDao;
import com.epam.brest.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) { this.employeeDao=employeeDao;
    }

    @Override
    public void riseAllSallerysByPercent(int percent) {
        LOGGER.debug("riseAllSallerysByPercent {}",percent);
        List<Employee> employees=employeeDao.getEmployees();
        for (Employee employee:employees
             )
        {

           int new_salary= employee.getEmployeeSalary();
            LOGGER.debug("Employee: {}:{} Old salary: {}",employee.getEmployeeName(),employee.getEmployeeId(),new_salary);
            new_salary+=new_salary*percent/100;
            LOGGER.debug("Employee: {}:{} New salary: {}",employee.getEmployeeName(),employee.getEmployeeId(),new_salary);
           employee.setEmployeeSalary(new_salary);
           employeeDao.updateEmployee(employee);
        }

    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }
}
