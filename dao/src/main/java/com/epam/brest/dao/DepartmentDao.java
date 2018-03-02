package com.epam.brest.dao;

import com.epam.brest.model.Department;


import java.util.List;

/**
 * Department access methods interface.
 */
public interface DepartmentDao {
    /**
     * method getDepartments is created to get all departments.
     *
     * @return List<Department> List of all Departments
     */
    List<Department> getDepartments();

    /**
     * method getDepartmentById is created to get department by its id.
     *
     * @param departmentId id of department to find
     * @return Department searched department
     */
    Department getDepartmentById(final int departmentId);
    /**
     * method getDepartmentByName is created to get department by its name.
     *
     * @param departmentName name of department to find
     * @return Department searched department
     */
    Department getDepartmentByName(final String departmentName);
    /**
     * method addDepartment is created to add department.
     *
     * @param department object to add into database
     * @return Department added object
     */
    Department addDepartment(Department department);
    /**
     * method updateDepartment is created to update department record.
     *
     * @param department object to update
     */
    void updateDepartment(Department department);
    /**
     * method removeDepartmentById is created to remove department from record.
     *
     * @param departmentid id of department to remove
     * @return flag of operation  coalition
     */
    boolean removeDepartmentById(final int departmentid);

}
