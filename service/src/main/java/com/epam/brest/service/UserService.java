package com.epam.brest.service;

import com.epam.brest.model.User;

public interface UserService {

    User getUserByLogin(String login);
}
