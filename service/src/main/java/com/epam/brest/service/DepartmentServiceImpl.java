package com.epam.brest.service;

import com.epam.brest.dao.DepartmentAVGsalaryDao;
import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private DepartmentAVGsalaryDao departmentAVGsalaryDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        LOGGER.debug("getDepartmentById{}",departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription(int departmentId, String newDescription) {
        LOGGER.debug("updateDepartmentDescription({},{})",departmentId,newDescription);
        Department department=departmentDao.getDepartmentById(departmentId);
        department.setDescription(newDescription);
        departmentDao.updateDepartment(department);

    }

    @Override
    public Collection<Department> getDepartments() {
        LOGGER.debug("getDepartments");
        return departmentDao.getDepartments();
    }

    @Override
    public Collection<DepartmentAVGsalary> getDepartmentsAVGSalary() {
        LOGGER.debug("getDepartmentsAVGSalary");
        return departmentAVGsalaryDao.getDepartmentsAVGSalary();
    }


}
