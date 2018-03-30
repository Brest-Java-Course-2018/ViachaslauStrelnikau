package com.epam.brest.dao;

import com.epam.brest.dto.GroupDTO;
import com.epam.brest.model.Group;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.xml", "classpath:test-dao.xml", "classpath:test-db-spring.xml"})
@Rollback
@Transactional
public class GroupDaoImplTest {

    private static final int GROUPID = 100;
    private static final String SHORTNAME = "Test";
    private static final String FULLNAME = "Test field";
    private static final String DESCRIPTION = "Some description";

    private static final String SHORTNAME2 = "Test2";
    private static final String FULLNAME2 = "Test field2";
    private static final String DESCRIPTION2 = "Some description2";

    @Autowired
    GroupDao groupDao;

    @Test
    public void getallGroupDTOTest() {
        Collection<GroupDTO> groupDTOS = groupDao.getallGroupsDTO();
        Assert.assertFalse(groupDTOS.isEmpty());
    }

    @Test
    public void getgroupByIdTest() {
        Group group = new Group(SHORTNAME,FULLNAME,DESCRIPTION);
        Group group_out = groupDao.addGroup(group);
        Group groupbyId=groupDao.getGroupById(group_out.getGroupId());

        Assert.assertTrue(groupbyId.getShortName().equals(SHORTNAME));
        Assert.assertTrue(groupbyId.getFullName().equals(FULLNAME));
        Assert.assertTrue(groupbyId.getDescription().equals(DESCRIPTION));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getgroupByIdTest2() {
        Group group = groupDao.getGroupById(GROUPID);
    }

    @Test
    public void addGroup()
    {
        Group group = new Group(SHORTNAME,FULLNAME,DESCRIPTION);
        int size_before = groupDao.getallGroupsDTO().size();
        Group group_out = groupDao.addGroup(group);
        int size_after = groupDao.getallGroupsDTO().size();

        Assert.assertTrue(size_before+1==size_after);
        Assert.assertNotNull(group_out);
        Assert.assertTrue(group_out.getShortName().equals(SHORTNAME));
        Assert.assertTrue(group_out.getFullName().equals(FULLNAME));
        Assert.assertTrue(group_out.getDescription().equals(DESCRIPTION));

    }
    @Test(expected = IllegalArgumentException.class)
    public void addGroup2()
    {
        Group group = new Group(SHORTNAME,FULLNAME,DESCRIPTION);
        groupDao.addGroup(group);
        groupDao.addGroup(group);
    }
    @Test
    public void updateGroup()
    {
        Group group = new Group(SHORTNAME,FULLNAME,DESCRIPTION);
        Group group_out = groupDao.addGroup(group);
        group_out.setShortName(SHORTNAME2);
        group_out.setFullName(FULLNAME2);
        group_out.setDescription(DESCRIPTION2);

        groupDao.updateGroup(group_out);

        Group group_upd=groupDao.getGroupById(group_out.getGroupId());

        Assert.assertTrue(group_out.getShortName().equals(group_out.getShortName()));
        Assert.assertTrue(group_out.getFullName().equals(group_out.getFullName()));
        Assert.assertTrue(group_out.getDescription().equals(group_out.getDescription()));
    }

    @Test
    public void removeGroup()
    {
        Group group = new Group(SHORTNAME,FULLNAME,DESCRIPTION);
        Group group_out = groupDao.addGroup(group);
        int size_before=groupDao.getallGroupsDTO().size();
        groupDao.deleteGroup(group_out.getGroupId());
        int size_after=groupDao.getallGroupsDTO().size();

        Assert.assertTrue(size_before==size_after+1);
    }
}