package com.epam.brest.dao;

import com.epam.brest.model.DepartmentAVGsalary;

import java.util.Collection;

public interface DepartmentAVGsalaryDao {
    /**
     * method getDepartments is created to get all departments.
     *
     * @return Collection  of all Departments avg Salary
     */
    Collection<DepartmentAVGsalary> getDepartmentsAVGSalary();
}
