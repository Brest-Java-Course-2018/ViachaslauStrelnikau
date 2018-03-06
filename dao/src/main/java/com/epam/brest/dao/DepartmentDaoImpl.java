package com.epam.brest.dao;

import com.epam.brest.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Department access methods .
 */
public class DepartmentDaoImpl implements DepartmentDao {
    public static final String DEPARTMENT_ID = "department_id";
    public static final String NAME = "department_name";
    public static final String DESCRIPTION = "description";

    @Value("${department.select}")
    private String departmentSelect;

    @Value("${department.slectByid}")
    private String departmentSelectbyId;

    @Value("${department.checkdepartment}")
    private String departmentCheck;

    @Value("${department.insert}")
    private String departmentInsert;

    @Value("${department.update}")
    private String departmentUpadate;

    @Value("${department.remove}")
    private String departmentRemove;


    /**
     * Property namedParameterJdbcTemplate.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    /**
     * method getDepartments is created to get all departments.
     *
     * @return List<Department> List of all Departments
     */
    @Override
    public List<Department> getDepartments() {
        List<Department> departments = namedParameterJdbcTemplate.getJdbcOperations().query(departmentSelect, BeanPropertyRowMapper.newInstance(Department.class));
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
    public Department getDepartmentById(final int departmentId)
    {
        SqlParameterSource namedParametres =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department=
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
    // проверка через количество привести в один ригистр
    @Override
    public Department addDepartment(Department department) {
        SqlParameterSource namedParametres1 =
                new MapSqlParameterSource(NAME, department.getDepartmentName());
       Integer result= namedParameterJdbcTemplate.queryForObject(departmentCheck,namedParametres1,  Integer.class);

       if(result==0)
       {
           SqlParameterSource namedParametres =
                   new MapSqlParameterSource(NAME, department.getDepartmentName());
           ((MapSqlParameterSource) namedParametres).addValue(DESCRIPTION, department.getDescription());

           KeyHolder generateKey = new GeneratedKeyHolder() ;
           namedParameterJdbcTemplate.update(departmentInsert,namedParametres,generateKey);
           department.setDepartmentId(generateKey.getKey().intValue());
     }
       else
       throw new IllegalArgumentException("Record is in base");

       return  department;
    }

    /**
     * method updateDepartment is created to update department record.
     *
     * @param department object to update
     */
    @Override
    public void updateDepartment(Department department) {
        SqlParameterSource namedParametres =new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(departmentUpadate,namedParametres);
    }

    /**
     * method removeDepartmentById is created to remove department from record.
     *
     * @param departmentid id of department to remove
     */
    @Override
    public void removeDepartmentById(final int departmentid) {
        namedParameterJdbcTemplate.getJdbcOperations().update(departmentRemove,departmentid);
    }
}
