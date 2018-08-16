package com.epam.brest.service;

import com.epam.brest.dao.UserDao;
import com.epam.brest.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    /**
     * Logger initilization.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    UserDao userDao;
    @Override
    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin:{}",login);
        User user=userDao.getUserByLogin(login);
        return user;
    }
}
