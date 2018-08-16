package com.epam.brest.dao;

import com.epam.brest.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();

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
     * SQL statement to select users by login.
     */
    @Value("${user.getuserbylogin}")
    private String sql_getuserbylogin;

    /**
     * SQL statement to select users by login.
     */
    @Value("${user.checkuserbylogin}")
    private String sql_checkuserbylogin;

    /**
     * method getUserByLogin get users record by its login.
     * @param login users login
     * @return users record
     */
    @Override
    public User getUserByLogin(String login) {

        LOGGER.debug("UserDao getUserByLogin:{}", login);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("username", login);

        Integer result = namedParameterJdbcTemplate
                .queryForObject(sql_checkuserbylogin, mapSqlParameterSource, Integer.class);
        if (result == 0) {
            LOGGER.error("UserDao getUserByLogin - Record is absent");
            throw new IllegalArgumentException("Record is absent");
        }

        User user = namedParameterJdbcTemplate
                .queryForObject(sql_getuserbylogin, mapSqlParameterSource, BeanPropertyRowMapper.newInstance(User.class));
        return user;
    }
}
