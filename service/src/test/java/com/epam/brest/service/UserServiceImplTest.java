package com.epam.brest.service;

import com.epam.brest.dao.UserDao;
import com.epam.brest.model.User;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class UserServiceImplTest {

    private User testUser;


    @Before
    public void testSetUp()
    {
        testUser= new User( 1111,"test","test");
    }

    @Autowired
    UserService userService;

    @Autowired
    UserDao mocUserDao;

    @Test
    public void getUserByLoginTest()
    {
        EasyMock.expect(mocUserDao.getUserByLogin("test")).andReturn(testUser);
        EasyMock.replay(mocUserDao);

        userService.getUserByLogin("test");

        EasyMock.verify(mocUserDao);
        EasyMock.reset(mocUserDao);
    }
}