package com.epam.brest.dao;

import com.epam.brest.model.Department;

import java.util.List;

/**
 * Department access methods interface.
 */
public interface DepartmentDao {
    /**
     * Get list of departments.
     * @return  List<Department>
     */
    List<Department> getDepartments();
    /**
     * Get department by ID.
     * @param departmentId
     * @return Department object
     */
    Department getDepartmentById(final Integer departmentId);

}
