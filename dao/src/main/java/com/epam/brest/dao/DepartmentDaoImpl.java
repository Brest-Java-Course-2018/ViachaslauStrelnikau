package com.epam.brest.dao;
import com.epam.brest.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.jdbc.JdbcTestUtils;


import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
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
            "from department" +
            " where department_id=:department_id";
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

    @Override
    public Department addDepartment(Department department) {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public boolean removeDepartmentById(int departmentid) {
        return false;
    }

}
