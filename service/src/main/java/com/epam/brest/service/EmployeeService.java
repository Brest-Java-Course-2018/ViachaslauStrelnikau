package com.epam.brest.service;

import com.epam.brest.model.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * method riseAllSallerysByPercent is created to rise Salary by percent value for all Employees.
     *
     * @param percent percent of rising
     */
    void riseAllSallerysByPercent(int percent);
    /**
     * method getEmployees is created to get all Employees.
     *
     * @return List  of all Employees
     */
    List<Employee> getEmployees();
    /**
     * method getEmployeeById is created to get employee by its id.
     *
     * @param employeeId id of department to find
     * @return Employee searched employee
     */
    Employee getEmployeeById(final int employeeId);
    /**
     * method removeEmployeeById is created to remove employee from base.
     *
     * @param employeeId id of department to remove
     */
    void removeEmployeeById(final int employeeId);
}
