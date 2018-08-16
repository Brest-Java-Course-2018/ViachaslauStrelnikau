package com.epam.brest.dao;

import com.epam.brest.model.User;

public interface UserDao {
    User getUserByLogin(String login);
}
