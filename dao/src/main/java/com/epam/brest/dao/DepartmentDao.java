package com.epam.brest.dao;

import com.epam.brest.model.Department;
import java.util.Collection;

/**
 * Department access methods interface.
 */
public interface DepartmentDao {
    /**
     * method getDepartments is created to get all departments.
     *
     * @return List List of all Departments
     */
    Collection<Department> getDepartments();

    /**
     * method getDepartmentById is created to get department by its id.
     *
     * @param departmentId id of department to find
     * @return Department searched department
     */
    Department getDepartmentById(final int departmentId);
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
     */
    void removeDepartmentById(final int departmentid);

}
