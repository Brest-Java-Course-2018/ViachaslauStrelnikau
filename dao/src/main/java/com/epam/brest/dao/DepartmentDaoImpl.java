package com.epam.brest.dao;

import com.epam.brest.model.Department;
import com.epam.brest.model.DepartmentAVGsalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

/**
 * Department access methods .
 */
public class DepartmentDaoImpl implements DepartmentDao {
    private static final Logger LOGGER= LogManager.getLogger();

    /**
     * Property const DEPARTMENT_ID .
     */
    public static final String DEPARTMENT_ID = "department_id";
    /**
     * Property const NAME .
     */
    public static final String NAME = "department_name";
    /**
     * Property const DESCRIPTION .
     */
    public static final String DESCRIPTION = "description";

    /**
     * SQL statement to select all departments .
     */
    @Value("${department.select}")
    private String departmentSelect;

    /**
     * SQL statement to select department by id .
     */
    @Value("${department.slectByid}")
    private String departmentSelectbyId;
    /**
     * SQL statement to check unique of department name .
     */
    @Value("${department.checkdepartment}")
    private String departmentCheck;
    /**
     * SQL statement to insert department.
     */
    @Value("${department.insert}")
    private String departmentInsert;
    /**
     * SQL statement to update department.
     */
    @Value("${department.update}")
    private String departmentUpadate;
    /**
     * SQL statement to remove department.
     */
    @Value("${department.remove}")
    private String departmentRemove;

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
     * @return List List of all Departments
     */
    @Override
    public Collection<Department> getDepartments() {
        LOGGER.debug("getDepartments");

        Collection<Department> departments = namedParameterJdbcTemplate.getJdbcOperations().query(departmentSelect, BeanPropertyRowMapper.newInstance(Department.class));
        return departments;
    }

    /**
     * method getDepartmentById is created to get department by its id.
     *
     * @param departmentId id of department to find
     * @return Department searched department
     */
    // нельзя возвращать null
    @Override
    public Department getDepartmentById(final int departmentId) {
        LOGGER.debug("getDepartmentById {}",departmentId);

        SqlParameterSource namedParametres =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(departmentSelectbyId,
                        namedParametres, BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
     * method addDepartment is created to add department.
     *
     * @param department object to add into database
     * @return Department added object
     */
    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment {}",department);

        SqlParameterSource namedParametres1 =
                new MapSqlParameterSource(NAME, department.getDepartmentName());
        Integer result = namedParameterJdbcTemplate.queryForObject(departmentCheck, namedParametres1, Integer.class);

        LOGGER.debug("Result = {}",result);
        if (result == 0) {
            MapSqlParameterSource namedParametres =new MapSqlParameterSource();

            namedParametres.addValue(NAME, department.getDepartmentName());
            namedParametres.addValue(DESCRIPTION, department.getDescription());

            KeyHolder generateKey = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(departmentInsert, namedParametres, generateKey);
            department.setDepartmentId(generateKey.getKey().intValue());
        } else {
            LOGGER.error("Record is in base");
            throw new IllegalArgumentException("Record is in base");
        }

        return department;
    }

    /**
     * method updateDepartment is created to update department record.
     *
     * @param department object to update
     */
    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("updateDepartment {}",department);

        SqlParameterSource namedParametres = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(departmentUpadate, namedParametres);
    }

    /**
     * method removeDepartmentById is created to remove department from base.
     *
     * @param departmentid id of department to remove
     */
    @Override
    public void removeDepartmentById(final int departmentid) {
        LOGGER.debug("removeDepartmentById {}",departmentid);
        
        namedParameterJdbcTemplate.getJdbcOperations().update(departmentRemove, departmentid);
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
