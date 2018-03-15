package com.epam.brest.service;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface DepartmentService {
    Department getDepartmentById(final int departmentId);

    void updateDepartmentDescription(int departmentId,String newDescription);
    /**
     * method getDepartments is created to get all departments.
     *
     * @return List List of all Departments
     */
    Collection<Department> getDepartments();
    /**
     * method getDepartments is created to get all departments.
     *
     * @return Collection  of all Departments avg Salary
     */
    Collection<DepartmentAVGsalary> getDepartmentsAVGSalary();
}
