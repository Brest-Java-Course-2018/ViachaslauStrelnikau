package com.epam.brest.service;

import com.epam.brest.model.Department;

public interface DepartmentService {
    Department getDepartmentById(final int departmentId);
    void updateDepartmentDescription(int departmentId,String newDescription);
}
