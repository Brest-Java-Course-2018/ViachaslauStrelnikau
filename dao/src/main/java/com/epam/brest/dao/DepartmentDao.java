package com.epam.brest.dao;

import com.epam.brest.model.Department;

import java.util.List;

/**
 * Department access methods interface
 */
public interface DepartmentDao {
    /**
     * Get list of departments
     */
    List<Department> getDepartments();
    /**
     * Get department by ID
     */
    Department getDepartmentById(final Integer departmentId);

}
