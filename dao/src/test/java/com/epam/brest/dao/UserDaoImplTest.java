package com.epam.brest.dao;

import com.epam.brest.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.xml", "classpath:test-dao.xml", "classpath:test-db-spring.xml"})
@Rollback
@Transactional
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    public final static User USER = new User(1,"loiane","$2a$10$2a4e8803c91cc5edca222evoNPfhdRyGEG9RZcg7.qGqTjuCgXKda");

    @Test(expected = IllegalArgumentException.class)
    public void getUserByLoginTest()
    {
        User user=userDao.getUserByLogin("no record");
    }

    @Test
    public void UserDaoImplTest2() throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException
    {
        User user=userDao.getUserByLogin("loiane");
        Assert.assertNotNull(user);
        Assert.assertEquals(USER.getUserId(),user.getUserId());
        Assert.assertEquals(USER.getUserName(),user.getUserName());
        Assert.assertEquals(USER.getUserPassword(),user.getUserPassword());



    }
}