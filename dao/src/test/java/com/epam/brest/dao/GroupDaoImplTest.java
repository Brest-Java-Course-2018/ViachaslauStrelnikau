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
@ContextConfiguration(locations = {"classpath:dao.xml","classpath:test-dao.xml","classpath:test-db-spring.xml"})
@Rollback
@Transactional
public class GroupDaoImplTest {
    @Autowired
    GroupDao groupDao;
    @Test
    public void getallGroupDTOTest()
    {
        Collection<GroupDTO> groupDTOS =groupDao.getallGroupsDTO();
        Assert.assertFalse(groupDTOS.isEmpty());
    }

    @Test
    public void getgroupByIdTest()
    {
        Group group = groupDao.getGroupById(1);
        assertNotNull(group);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getgroupByIdTest2()
    {
        Group group = groupDao.getGroupById(100);
    }
}