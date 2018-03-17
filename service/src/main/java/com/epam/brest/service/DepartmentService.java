package com.epam.brest.service;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface DepartmentService {



    void updateDepartmentDescription(int departmentId,String newDescription);
    /**
     * method getDepartmentById is created to get department by its id.
     *
     * @param departmentId id of department to find
     * @return Department searched department
     */
    Department getDepartmentById(final int departmentId);
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
    /**
     * method removeDepartmentById is created to remove department from record.
     *
     * @param departmentid id of department to remove
     */
    Department removeDepartmentById(final int departmentid);
    /**
     * method addDepartment is created to add department.
     *
     * @param department object to add into database
     * @return Department added object
     */
    Department addDepartment(Department department);
}
