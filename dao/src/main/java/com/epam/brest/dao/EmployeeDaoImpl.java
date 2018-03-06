package com.epam.brest.dao;

import com.epam.brest.model.Department;
import com.epam.brest.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    /**
     * Property const DEPARTMENT_ID .
     */
    public static final String EMPLOYEE_ID = "employee_id";
    /**
     * Property const DEPARTMENT_ID .
     */
    public static final String DEPARTMENT_ID = "department_id";
    /**
     * Property const EMPLOYEE_NAME .
     */
    public static final String EMPLOYEE_NAME = "employee_name";
    /**
     * Property const EMPLOYEESALARY .
     */
    public static final String EMPLOYEESALARY = "employeeSalary";

    @Value("${employee.select}")
    private String employeeSelect;
    @Value("${employee.selectByid}")
    private String employeeSelectByid;
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


    @Override
    public List<Employee> getEmployees() {
        List<Employee> Employees=namedParameterJdbcTemplate.getJdbcOperations().query(employeeSelect, BeanPropertyRowMapper.newInstance(Employee.class));
        return Employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {

        SqlParameterSource namedParametres =
                new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        Employee employee =
                namedParameterJdbcTemplate.queryForObject(employeeSelectByid,
                        namedParametres, BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void removeEmployeeById(int employeeId) {

    }
}
