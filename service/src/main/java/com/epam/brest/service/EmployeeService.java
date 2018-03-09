package com.epam.brest.service;

import com.epam.brest.model.Employee;

import java.util.List;

public interface EmployeeService {
    void riseAllSallerysByPercent(int percent);
    List<Employee> getEmployees();
}
