package com.epam.brest.dao;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.model.Group;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

public class GroupDaoImpl implements GroupDao {
    /**
     * Logger initilization.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String GROUP_ID = "groupId";
    public static final String GROUP_SHORT_NAME = "shortName";
    public static final String GROUP_FULLNAME = "fullName";
    public static final String DESCRIPTION = "description";

    @Value("${group.dto}")
    private String sql_groupDAOselect;

    @Value("${group.getgroupbyid}")
    private String sql_groupById;

    @Value("${group.checkgroupid}")
    private String sql_checkGroup;

    @Value("${group.addrecord}")
    private String sql_addGroup;

    @Value("${group.updaterecord}")
    private String sql_updateGroup;

    @Value("${group.remove}")
    private String sql_removeGroup;
    /**
     * Property namedParameterJdbcTemplate.
     */
    /**
     * method setNamedParameterJdbcTemplate seter method for namedParameterJdbcTemplate property.
     *
     * @param namedParameterJdbcTemplate input value
     */
    public void setNamedParameterJdbcTemplate(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * method getallGroupsDTO gets all group DTO objects.
     *
     * @return Collection of group DTO
     */
    @Override
    public Collection<GroupDTO> getallGroupsDTO() {
        LOGGER.debug("GroupDao getallGroups");
        Collection<GroupDTO> groupDTOS = namedParameterJdbcTemplate.getJdbcOperations()
                .query(sql_groupDAOselect, BeanPropertyRowMapper.newInstance(GroupDTO.class));

        return groupDTOS;
    }

    /**
     * method getGroupById returns group by its ID.
     *
     * @param id id of group
     * @return Group searched group
     */
    @Override
    public Group getGroupById(int id) {
        LOGGER.debug("GroupDao getGroupById {}", id);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(GROUP_ID, id);
        Integer result = namedParameterJdbcTemplate.queryForObject(sql_checkGroup, mapSqlParameterSource, Integer.class);
        if (result == 0) {
            LOGGER.error("GroupDao getGroupById - Record is absent");
            throw new IllegalArgumentException("Record is absent");
        }

        Group group = namedParameterJdbcTemplate.queryForObject(sql_groupById, mapSqlParameterSource,
                BeanPropertyRowMapper.newInstance(Group.class));
        return group;
    }

    /**
     * method addGroup addes Griup record to database.
     *
     * @param group group
     * @return Group added group
     */
    @Override
    public Group addGroup(Group group) {
        LOGGER.debug("GroupDao addGroup {}", group);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(GROUP_SHORT_NAME, group.getShortName());
        mapSqlParameterSource.addValue(GROUP_FULLNAME, group.getFullName());

        Integer result = namedParameterJdbcTemplate.queryForObject(sql_addGroup, mapSqlParameterSource, Integer.class);

        if (result == 0) {
            mapSqlParameterSource.addValue(DESCRIPTION, group.getDescription());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql_addGroup, mapSqlParameterSource, keyHolder);
            group.setGroupId(keyHolder.getKey().intValue());
            return group;
        } else {
            LOGGER.error("GroupDao addGroup - Record with this name is in base");
            throw new IllegalArgumentException("Record with this name is in base");
        }
    }

    /**
     * method updateGroup update Group record in database.
     *
     * @param group group
     */
    @Override
    public void updateGroup(Group group) {
        LOGGER.debug("GroupDao updateGroup {}", group);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(GROUP_SHORT_NAME, group.getShortName());
        mapSqlParameterSource.addValue(GROUP_FULLNAME, group.getFullName());
        Integer result = namedParameterJdbcTemplate.queryForObject(sql_addGroup, mapSqlParameterSource, Integer.class);

        if (result == 0) {
            mapSqlParameterSource.addValue(DESCRIPTION, group.getDescription());
            mapSqlParameterSource.addValue(GROUP_ID, group.getGroupId());
            namedParameterJdbcTemplate.update(sql_updateGroup, mapSqlParameterSource);
        } else {
            LOGGER.error("GroupDao addGroup - Record with this name is in base");
            throw new IllegalArgumentException("Record with this name is in base");
        }

    }

    /**
     * method deleteGroup remove Group record from database.
     *
     * @param id id of record to remove
     */
    @Override
    public void deleteGroup(int id) {
        LOGGER.debug("GroupDao deleteGroup {}", id);
        namedParameterJdbcTemplate.getJdbcOperations().update(sql_removeGroup, id);
    }

}
