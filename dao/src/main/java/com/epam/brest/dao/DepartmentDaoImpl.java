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
    /**
     * Property jdbcTemplate.
     */
  //  private JdbcTemplate jdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

//    /**
//     * Property namedParameterJdbcTemplate.
//     */
//
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    /**
//     * Property get_departments_sql sql statement.
//     */
//    private static final String get_departments_sql =
//            "select department_id,department_name, description from department";
//    /**
//     * Property get_department_by_id_sql sql statement.
//     */
//    private static final String get_department_by_id_sql =
//            "select department_id,department_name, description "
//                    + "from department where department_id = :department_id";
//    /**
//     * Property add_department_sql sql statement.
//     */
//    private static final String add_department_sql =
//            "insert into department(department_name,description)\n"
//                    + "values (:department_name,:description);";
//
//    private static final String check_department_sql="select count(department_id) from department " +
//            "where lower( department_name)= lower(:department_name)";
//    /**
//     * Property get_department_by_name_sql sql statement.
//     */
//    private static final String get_department_by_name_sql =
//            "select department_id,department_name, description "
//                    + "from department where department_name = :department_name";
//    /**
//     * Property update_department_sql sql statement.
//     */
//
//
//    private static final String update_department_sql = "UPDATE department\n"
//            + "SET department_name = :departmentName, description = :description\n" +
//            "WHERE department_id = :departmentId;";
//    /**
//     * Property remove_department_by_id_sql sql statement.
//     */
//    private static final String remove_department_by_id_sql = "DELETE FROM department\n"
//            + "WHERE department_id = ?;";

    @Value("${department.select}")
    private String departmentSelect;
    @Value("${department.slectByid}")
    private String departmentSelectbyId;

    @Value("${department.insert}")
    private String departmentInsert;

    @Value("${department. department.upadate}")
    private String departmentUpadate;

    @Value("${department.remove}")
    private String departmentRemove;







    /**
     * method getDepartments is created to get all departments.
     *
     * @return List<Department> List of all Departments
     */
    @Override
    public List<Department> getDepartments() {
        List<Department> departments = namedParameterJdbcTemplate.getJdbcOperations().query(departmentSelect, new DepartmentrowMapper());
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
//    public Department getDepartmentById(final int departmentId) {
//
//
//        SqlParameterSource namedParametres =
//                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
//        Department department;
//        try {
//            department = namedParameterJdbcTemplate.queryForObject(get_department_by_id_sql, namedParametres, new DepartmentrowMapper());
//        } catch (Exception e) {
//            return null;
//        }
//        return department;
//    }
    public Department getDepartmentById(final int departmentId)
    {
        SqlParameterSource namedParametres =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department=
                namedParameterJdbcTemplate.queryForObject(departmentSelectbyId,
                        namedParametres, BeanPropertyRowMapper.newInstance(Department.class));
    }

    /**
     * method getDepartmentByName is created to get department by its name.
     *
     * @param departmentName name of department to find
     * @return Department searched department
     */
    @Override
    public Department getDepartmentByName(final String departmentName) {
        SqlParameterSource namedParametres =
                new MapSqlParameterSource(NAME, departmentName);
        Department department;
        try {
            department =
                    namedParameterJdbcTemplate.queryForObject(get_department_by_name_sql, namedParametres, new DepartmentrowMapper());
        } catch (Exception e) {
            return null;
        }
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
       Integer res= namedParameterJdbcTemplate.queryForObject(check_department_sql,namedParametres1,  Integer.class);

       if(res==0)
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

       return
               department;
//
//        if (getDepartmentByName(department.getDepartmentName()) != null)
//            return null;
//        else {
//
//            SqlParameterSource namedParametres = new MapSqlParameterSource("department_name", department.getDepartmentName());
//            ((MapSqlParameterSource) namedParametres).addValue("description", department.getDescription());
//
//            namedParameterJdbcTemplate.execute(add_department_sql, namedParametres, new PreparedStatementcallback());
//
//            return department;
//        }
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
//        Department department_old = getDepartmentById(department.getDepartmentId());
//        String departmentNametoUpdate = department.getDepartmentName();
//
//        Department department_byname = getDepartmentByName(department.getDepartmentName());
//        // checking are we going to update record to department name that is already exists
//        if (department_byname != null && department_old.getDepartmentId() != department_byname.getDepartmentId()) {
//            departmentNametoUpdate = department_old.getDepartmentName();
//        }
//
//        SqlParameterSource namedParametres = new MapSqlParameterSource(NAME, departmentNametoUpdate);
//        ((MapSqlParameterSource) namedParametres).addValue(DESCRIPTION, department.getDescription());
//        ((MapSqlParameterSource) namedParametres).addValue(DEPARTMENT_ID, department.getDepartmentId());
//        namedParameterJdbcTemplate.execute(update_department_sql, namedParametres, new PreparedStatementcallback());
    }

    /**
     * method removeDepartmentById is created to remove department from record.
     *
     * @param departmentid id of department to remove
     * @return flag of operation  coalition
     */
    @Override
    public boolean removeDepartmentById(final int departmentid) {

        SqlParameterSource namedParametres =
                new MapSqlParameterSource(NAME, departmentid);
        namedParameterJdbcTemplate.getJdbcOperations().update(remove_department_by_id_sql,namedParametres);

        return true;
//        SqlParameterSource namedParametres = new MapSqlParameterSource(DEPARTMENT_ID, departmentid);
//        int result = (int) namedParameterJdbcTemplate.execute(remove_department_by_id_sql, namedParametres, new PreparedStatementcallback());
//        if (result == 1) {
//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * Class DepartmentrowMapper is entire class that mapa ResaultSet records to Department objects.
     */
    private class DepartmentrowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(final ResultSet resultSet, final int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(NAME));
            department.setDescririon(resultSet.getString(DESCRIPTION));
            return department;
        }
    }

    /**
     * Class PreparedStatementcallback is entire class that executes SQL PreparedStatemets .
     */
    private class PreparedStatementcallback implements PreparedStatementCallback {
        @Override
        public Object doInPreparedStatement(PreparedStatement ps)
                throws SQLException, DataAccessException {
            return ps.executeUpdate();
        }
    }

}
