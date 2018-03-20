package com.epam.brest.service;

import com.epam.brest.dao.EmployeeDao;
import com.epam.brest.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * method riseAllSallerysByPercent is created to rise Salary by percent value for all Employees.
     *
     * @param percent percent of rising
     */
    @Override
    public void riseAllSallerysByPercent(int percent) {
        LOGGER.debug("EmployeeService.riseAllSallerysByPercent {}", percent);
        List<Employee> employees = employeeDao.getEmployees();
        for (Employee employee : employees
                ) {

            int new_salary = employee.getEmployeeSalary();
            LOGGER.debug("Employee: {}:{} Old salary: {}", employee.getEmployeeName(), employee.getEmployeeId(), new_salary);
            new_salary += new_salary * percent / 100;
            LOGGER.debug("Employee: {}:{} New salary: {}", employee.getEmployeeName(), employee.getEmployeeId(), new_salary);
            employee.setEmployeeSalary(new_salary);
            employeeDao.updateEmployee(employee);
        }
    }

    /**
     * method getEmployees is created to get all Employees.
     *
     * @return List  of all Employees
     */
    @Override
    public List<Employee> getEmployees() {
        LOGGER.debug("EmployeeService.getEmployees");
        return employeeDao.getEmployees();
    }

    /**
     * method getEmployeeById is created to get employee by its id.
     *
     * @param employeeId id of department to find
     * @return Employee searched employee
     */
    @Override
    public Employee getEmployeeById(int employeeId) {
        LOGGER.debug("EmployeeService.getEmployeeById {}",employeeId);
        return employeeDao.getEmployeeById(employeeId);
    }

    /**
     * method removeEmployeeById is created to remove employee from base.
     *
     * @param employeeId id of department to remove
     */
    public void removeEmployeeById(final int employeeId) {
        LOGGER.debug("EmployeeService.removeEmployeeById {}",employeeId);
        employeeDao.removeEmployeeById(employeeId);
    }
    /**
     * method addEmployee is created to add employee.
     *
     * @param employee object to add into database
     * @return Department added object
     */
    @Override
    public Employee addEmployee(Employee employee) {
        LOGGER.debug("EmployeeService.addEmployee {}",employee);
        return employeeDao.addEmployee(employee);
    }
    /**
     * method updateEmployee is created to update employee record.
     *
     * @param employee object to update
     */
    @Override
    public void updateEmployee(Employee employee) {
        LOGGER.debug("EmployeeService.updateEmployee {}",employee);
        employeeDao.updateEmployee(employee);
    }

}
