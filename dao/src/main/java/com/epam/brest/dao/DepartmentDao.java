package com.epam.brest.dao;

import com.epam.brest.model.Department;

import java.util.List;

/**
 * Department access methods interface
 */
public interface DepartmentDao {
    List<Department>getDepartments();
    Department getDepartmentById(Integer departmentId);

}
