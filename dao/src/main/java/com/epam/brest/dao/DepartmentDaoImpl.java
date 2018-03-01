package com.epam.brest.dao;
import com.epam.brest.model.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * Department access methods .
 */
public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String get_departments_sql="select department_id,department_name, description from department";
    private final String get_department_by_id_sql="select department_id,department_name, description " +
            "from department where department_id=:department_id";
    private final String add_department_sql="insert into department(department_name,description)\n" +
            "values (:department_name,:description);";
    private final String get_department_by_name_sql="select department_id,department_name, description " +
            "from department where department_name=:department_name";
    private DepartmentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments= jdbcTemplate.query(get_departments_sql,new DepartmentrowMapper());
        return departments;
    }

    @Override
    public Department getDepartmentById(final Integer departmentId) {
        SqlParameterSource namedParametres = new MapSqlParameterSource("department_id",departmentId);
        return namedParameterJdbcTemplate.queryForObject(get_department_by_id_sql,namedParametres, new DepartmentrowMapper());
    }

    public Department getDepartmentByName(final String departmentName) {
        SqlParameterSource namedParametres = new MapSqlParameterSource("department_name",departmentName);
        Department department;
        try
        {
            department =namedParameterJdbcTemplate.queryForObject(get_department_by_name_sql,namedParametres, new DepartmentrowMapper());
        }catch (Exception e)
        {
            return null;
        }
        return department;
    }


    @Override
    public Department addDepartment(Department department) {
        if(getDepartmentByName(department.getDepartmentName())!=null)
        return null;
        else
        {

            SqlParameterSource namedParametres = new MapSqlParameterSource("department_name",department.getDepartmentName());
            ((MapSqlParameterSource)namedParametres).addValue("description",department.getDescription());

            namedParameterJdbcTemplate.execute(add_department_sql,namedParametres,  new PreparedStatementcallback());

            return department;
        }
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public boolean removeDepartmentById(int departmentid) {
        return false;
    }

    /**
     * Class DepartmentrowMapper is entire class that mapa ResaultSet records to Department objects.
     */
    private class DepartmentrowMapper implements org.springframework.jdbc.core.RowMapper<Department>
    {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDescririon(resultSet.getString(3));
            return department;
        }
    }
    /**
     * Class PreparedStatementcallback is entire class that executes SQL PreparedStatemets .
     */
    private class PreparedStatementcallback implements PreparedStatementCallback
    {
        @Override
        public Object doInPreparedStatement(PreparedStatement ps)
                throws SQLException, DataAccessException {
            return ps.executeUpdate();
        }
    }

}
