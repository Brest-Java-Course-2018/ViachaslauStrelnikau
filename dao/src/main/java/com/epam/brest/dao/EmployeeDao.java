package com.epam.brest.dao;
import com.epam.brest.model.Employee;

import java.util.List;

public interface EmployeeDao {
    /**
     * method getEmployees is created to get all employees.
     *
     * @return List List of all employees
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
     * method addEmployee is created to add employee.
     *
     * @param employee object to add into database
     * @return Department added object
     */
    Employee addEmployee(Employee employee);
    /**
     * method updateEmployee is created to update employee record.
     *
     * @param employee object to update
     */
    void updateEmployee(Employee employee);
    /**
     * method removeEmployeeById is created to remove employee from base.
     *
     * @param employeeId id of department to remove
     */
    void removeEmployeeById(final int employeeId);
}
