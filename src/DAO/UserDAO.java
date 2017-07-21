package DAO;

import Entity.User;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
public interface UserDAO {

    User getUserByEmail(String email);

    List<User> getAllUser();

    void addUser(User user);

    boolean delUser(String id);

    boolean updateUser(User user);

    User getUserByToken(String accessToken);
}
