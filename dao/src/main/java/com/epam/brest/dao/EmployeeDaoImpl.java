package com.epam.brest.dao;

import com.epam.brest.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    /**
     * Property const DEPARTMENT_ID .
     */
    public static final String EMPLOYEE_ID = "employee_id";

    @Value("${employee.select}")
    private String employeeSelect;
    @Value("${employee.selectByid}")
    private String employeeSelectByid;
    @Value("${employee.insert}")
    private String employeeInsert;
    @Value("${employee.update}")
    private String employeeUpadate;
    @Value("${employee.remove}")
    private String employeeRemove;

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
     * method getEmployees is created to get all employees.
     *
     * @return List List of all employees
     */
    @Override
    public List<Employee> getEmployees() {
        List<Employee> Employees = namedParameterJdbcTemplate.getJdbcOperations().query(employeeSelect, BeanPropertyRowMapper.newInstance(Employee.class));
        return Employees;
    }

    /**
     * method getEmployeeById is created to get employee by its id.
     *
     * @param employeeId id of department to find
     * @return Employee searched employee
     */
    @Override
    public Employee getEmployeeById(int employeeId) {

        SqlParameterSource namedParametres =
                new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        Employee employee =
                namedParameterJdbcTemplate.queryForObject(employeeSelectByid,
                        namedParametres, BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    /**
     * method addEmployee is created to add employee.
     *
     * @param employee object to add into database
     * @return Department added object
     */
    @Override
    public Employee addEmployee(Employee employee) {
        SqlParameterSource namedParametres = new BeanPropertySqlParameterSource(employee);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(employeeInsert, namedParametres, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    /**
     * method updateEmployee is created to update employee record.
     *
     * @param employee object to update
     */
    @Override
    public void updateEmployee(Employee employee) {
        SqlParameterSource namedParametres = new BeanPropertySqlParameterSource(employee);

        namedParameterJdbcTemplate.update(employeeUpadate, namedParametres);
    }

    /**
     * method removeEmployeeById is created to remove employee from base.
     *
     * @param employeeId id of department to remove
     */
    @Override
    public void removeEmployeeById(int employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(employeeRemove, employeeId);
    }
}
