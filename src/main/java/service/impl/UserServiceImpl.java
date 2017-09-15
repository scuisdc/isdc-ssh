package service.impl;

import dao.UserDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;


    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean signUp(User user) {
        if (userDAO.getUserByEmail(user.getEmail()) == null) {
            userDAO.addUser(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> listAll() {
        return userDAO.getAllUser();
    }

    @Override
    public User login(String email, String password) {
        User userByEmail = userDAO.getUserByEmail(email);
        return userByEmail != null && userByEmail.getPassword().equals(password) ? userByEmail : null;
    }

    @Override
    public User auth(String accessToken) {
        return userDAO.getUserByToken(accessToken);
    }
}
