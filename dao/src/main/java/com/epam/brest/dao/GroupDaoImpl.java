package com.epam.brest.dao;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.dto.GroupDTOlite;
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
    /**
     * Property statement to select all Group DAO records.
     */
    @Value("${group.dto}")
    private String sql_groupDAOselect;
    /**
     * Property statement to select all Group DAO records.
     */
    @Value("${group.dtolite}")
    private String sql_groupDAOLiteselect;
    /**
     * Property statement to select Group DAO record by id.
     */
    @Value("${group.getgroupbyid}")
    private String sql_groupById;
    /**
     * Property statement to check is record in the base.
     */
    @Value("${group.checkgroupid}")
    private String sql_checkGroup;
    /**
     * Property statement to check is record with such names in the base.
     */
    @Value("${group.cheackname}")
    private String sql_checkGroupbyName;
    /**
     * Property statement to add record into the base.
     */
    @Value("${group.addrecord}")
    private String sql_addGroup;
    /**
     * Property statement to update record in the base.
     */
    @Value("${group.updaterecord}")
    private String sql_updateGroup;
    /**
     * Property statement to remove record from the base.
     */
    @Value("${group.remove}")
    private String sql_removeGroup;
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
     * method getallGroupsDTOlite gets all group DTOlite objects.
     *
     * @return Collection of group DTOlite
     */
    @Override
    public Collection<GroupDTOlite> getallGroupsDTOlite() {
        LOGGER.debug("GroupDao getallGroupsDTOlite");
        Collection<GroupDTOlite> groupDTOS = namedParameterJdbcTemplate.getJdbcOperations()
                .query(sql_groupDAOLiteselect, BeanPropertyRowMapper.newInstance(GroupDTOlite.class));

        return groupDTOS;
    }

    /**
     * method getGroupById returns group by its ID.
     *
     * @param id id of group
     * @return Group searched group
     */
    @Override
    public Group getGroupById(final int id) {
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
    public Group addGroup(final Group group) {
        LOGGER.debug("GroupDao addGroup {}", group);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(GROUP_SHORT_NAME, group.getShortName());
        mapSqlParameterSource.addValue(GROUP_FULLNAME, group.getFullName());

        Integer result = namedParameterJdbcTemplate.queryForObject(sql_checkGroupbyName, mapSqlParameterSource, Integer.class);

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
    public void updateGroup(final Group group) {
        LOGGER.debug("GroupDao updateGroup {}", group);

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(GROUP_SHORT_NAME, group.getShortName());
        mapSqlParameterSource.addValue(GROUP_FULLNAME, group.getFullName());
        mapSqlParameterSource.addValue(DESCRIPTION, group.getDescription());
        mapSqlParameterSource.addValue(GROUP_ID, group.getGroupId());

        namedParameterJdbcTemplate.update(sql_updateGroup, mapSqlParameterSource);
    }

    /**
     * method removeGroup remove Group record from database.
     *
     * @param groupId id of record to remove
     */
    @Override
    public void removeGroup(final int groupId) {
        LOGGER.debug("GroupDao deleteGroup {}", groupId);
        namedParameterJdbcTemplate.getJdbcOperations().update(sql_removeGroup, groupId);
    }

}
