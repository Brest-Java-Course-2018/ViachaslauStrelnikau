package com.epam.brest.dao;
import com.epam.brest.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployees();
    Employee getEmployeeById(final int employeeId);
    Employee addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void removeEmployeeById(final int employeeId);
}
