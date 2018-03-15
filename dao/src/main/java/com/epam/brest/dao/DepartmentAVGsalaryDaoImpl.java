package com.epam.brest.dao;

import com.epam.brest.model.DepartmentAVGsalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collection;

public class DepartmentAVGsalaryDaoImpl implements DepartmentAVGsalaryDao{
    private static final Logger LOGGER= LogManager.getLogger();

    @Value("${department.avg}")
    private String departmentAvgSelect;

    /**
     * Property namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * method setNamedParameterJdbcTemplate seter method for namedParameterJdbcTemplate property.
     *
     * @param namedParameterJdbcTemplate input value
     */
    public void setNamedParameterJdbcTemplate(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    /**
     * method getDepartments is created to get all departments.
     *
     * @return Collection  of all Departments avg Salary
     */
    @Override
    public Collection<DepartmentAVGsalary> getDepartmentsAVGSalary() {
        LOGGER.debug("getDepartmentsAVGSalary");
        Collection<DepartmentAVGsalary> departments = namedParameterJdbcTemplate.getJdbcOperations().query(departmentAvgSelect, BeanPropertyRowMapper.newInstance(DepartmentAVGsalary.class));
        return departments;
    }
}
