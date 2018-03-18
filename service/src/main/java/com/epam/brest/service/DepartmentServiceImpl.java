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
        LOGGER.debug("Service.updateDepartmentDescription({},{})",departmentId,newDescription);
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
        LOGGER.debug("Service.getDepartmentById{}",departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }
    /**
     * method getDepartments is created to get all departments.
     *
     * @return List List of all Departments
     */
    @Override
    public Collection<Department> getDepartments() {
        LOGGER.debug("Service.getDepartments");
        return departmentDao.getDepartments();
    }
    /**
     * method getDepartments is created to get all departments.
     *
     * @return Collection  of all Departments avg Salary
     */
    @Override
    public Collection<DepartmentAVGsalary> getDepartmentsAVGSalary() {
        LOGGER.debug("Service.getDepartmentsAVGSalary");
        return departmentAVGsalaryDao.getDepartmentsAVGSalary();
    }
    /**
     * method removeDepartmentById is created to remove department from record.
     *
     * @param departmentid id of department to remove
     */
    @Override
    public void removeDepartmentById(int departmentid) {
        LOGGER.debug("Service.removeDepartmentById");
        departmentDao.removeDepartmentById(departmentid);
    }

    /**
     * method addDepartment is created to add department.
     *
     * @param department object to add into database
     * @return Department added object
     */
    public Department addDepartment(Department department) {
        LOGGER.debug("Service.addDepartment {}",department);
        return departmentDao.addDepartment(department);
    }

    /**
     * method updateDepartment is created to update department record.
     *
     * @param department object to update
     */
    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("Service.updateDepartment {}",department);
        departmentDao.updateDepartment(department);
    }


}
