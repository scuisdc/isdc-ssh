package service.impl;

import dao.UserDAO;
import dto.UserResponse;
import entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserDAO userDAO, ModelMapper modelMapper) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean signUp(User user) {
        if (userDAO.getUserByEmail(user.getEmail()) == null) {
            userDAO.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserResponse> listAll() {
        return userDAO.findAll().stream().map(p -> modelMapper.map(p, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse login(String email, String password) {
        User userByEmail = userDAO.getUserByEmail(email);
        return userByEmail != null && userByEmail.getPassword().equals(password) ? modelMapper.map(userByEmail, UserResponse.class) : null;
    }

    @Override
    public UserResponse auth(String accessToken) {
        return modelMapper.map(userDAO.getUserByToken(accessToken), UserResponse.class);
    }
}
