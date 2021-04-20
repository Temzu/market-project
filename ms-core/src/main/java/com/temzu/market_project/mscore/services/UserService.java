package com.temzu.market_project.mscore.services;

import com.temzu.market_project.mscore.model.User;

public interface UserService {
    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    User saveUser(User user);
}
