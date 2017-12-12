package dao;

import entity.User;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
public interface UserDAO extends IGenericDao<User> {

    User getUserByEmail(String email);

    User getUserByToken(String accessToken);

    User getUserByName(String userName);
}
