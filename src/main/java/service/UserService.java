package service;

import entity.User;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
public interface UserService {

    boolean signUp(User user);

    List<User> listAll();

    User login(String email, String password);

    User auth(String accessToken);
}
