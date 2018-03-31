package com.epam.brest.dao;

import com.epam.brest.dto.StudentDTO;
import com.epam.brest.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

public class StudentDaoImpl implements StudentDao {
    /**
     * Logger initilization.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    //  private static final String STUDENTID="studentId";
    private static final String STUDENT_NAME = "studentName";
    private static final String STUDENT_BIRTH = "studentBirth";
    private static final String STUDENT_AVG_MARKS = "studentAvgMarks";
    private static final String GROUPID = "groupId";

    @Value("${student.dto}")
    private String sql_studentsdto;
    @Value("${student.checkstudentid}")
    private String sql_chseckstudentid;
    @Value("${student.getbyid}")
    private String sql_getstudentbyid;
    @Value("${student.addrecord}")
    private String sql_addstudent;

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
     * method getallStudentsDTO gets all students.
     *
     * @return Collection of student DTO
     */
    @Override
    public Collection<StudentDTO> getallStudentsDTO() {
        LOGGER.debug("StudentDao getallStudentsDTO");
        Collection<StudentDTO> studentDTOS = namedParameterJdbcTemplate
                .getJdbcOperations().query(sql_studentsdto, BeanPropertyRowMapper.newInstance(StudentDTO.class));

        return studentDTOS;
    }

    /**
     * method getStudentById returns student by its ID.
     *
     * @param id id of student
     * @return Student searched student
     */
    @Override
    public Student getStudentById(final int id) {
        LOGGER.debug("StudentDao getStudentById {}", id);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("studentId", id);
        Integer result = namedParameterJdbcTemplate
                .queryForObject(sql_chseckstudentid, mapSqlParameterSource, Integer.class);
        if (result == 0) {
            LOGGER.error("StudentDao getStudentById - Record is absent");
            throw new IllegalArgumentException("Record is absent");
        }

        Student student = namedParameterJdbcTemplate
                .queryForObject(sql_getstudentbyid, mapSqlParameterSource, BeanPropertyRowMapper.newInstance(Student.class));
        return student;

    }

    /**
     * method addStudent addes student record to database.
     *
     * @param student student
     * @return Student added student
     */
    @Override
    public Student addStudent(final Student student) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(STUDENT_NAME, student.getStudentName());
        mapSqlParameterSource.addValue(STUDENT_BIRTH, student.getStudentBirth());
        mapSqlParameterSource.addValue(STUDENT_AVG_MARKS, student.getStudentAvgMarks());
        mapSqlParameterSource.addValue(GROUPID, student.getGroupId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql_addstudent, mapSqlParameterSource, keyHolder);
        student.setStudentId(keyHolder.getKey().intValue());
        return student;
    }

    @Override
    public void updateStudent(final Student student) {

    }

    @Override
    public void removeStudent(final int id) {

    }
}
