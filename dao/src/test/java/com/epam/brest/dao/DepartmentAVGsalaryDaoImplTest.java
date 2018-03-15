package com.epam.brest.dao;

import com.epam.brest.model.DepartmentAVGsalary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml",
        "classpath:dao.xml"})
@Rollback
@Transactional
/**
 * class DepartmentAVGsalaryDaoImplTest tests DepartmentAVGsalaryDao.
 */
public class DepartmentAVGsalaryDaoImplTest {

    @Autowired
    DepartmentAVGsalaryDao departmentAVGsalaryDao;

    /**
     * Testing getDepartmentsAVGSalary() method of DepartmentAVGsalaryDao.
     */
    @Test
    public void getAvgDepartmentsSalary() {
        Collection<DepartmentAVGsalary> departmentsAVGSalary = departmentAVGsalaryDao.getDepartmentsAVGSalary();
        Assert.assertFalse(departmentsAVGSalary.isEmpty());
    }


}