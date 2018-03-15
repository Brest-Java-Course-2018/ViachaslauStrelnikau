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
    public void updateDepartmentDescription(int departmentId, String newDescription) {
        LOGGER.debug("updateDepartmentDescription({},{})",departmentId,newDescription);
        Department department=departmentDao.getDepartmentById(departmentId);
        department.setDescription(newDescription);
        departmentDao.updateDepartment(department);

    }

    /**
     * method getDepartmentById is created to get department by its id.
     *
     * @param departmentId id of department to find
     * @return Department searched department
     */
    @Override
    public Department getDepartmentById(int departmentId) {
        LOGGER.debug("getDepartmentById{}",departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }
    /**
     * method getDepartments is created to get all departments.
     *
     * @return List List of all Departments
     */
    @Override
    public Collection<Department> getDepartments() {
        LOGGER.debug("getDepartments");
        return departmentDao.getDepartments();
    }
    /**
     * method getDepartments is created to get all departments.
     *
     * @return Collection  of all Departments avg Salary
     */
    @Override
    public Collection<DepartmentAVGsalary> getDepartmentsAVGSalary() {
        LOGGER.debug("getDepartmentsAVGSalary");
        return departmentAVGsalaryDao.getDepartmentsAVGSalary();
    }
    /**
     * method removeDepartmentById is created to remove department from record.
     *
     * @param departmentid id of department to remove
     */
    @Override
    public Department removeDepartmentById(int departmentid) {
        return departmentDao.removeDepartmentById(departmentid);
    }


}
