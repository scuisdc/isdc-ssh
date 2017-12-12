package controller;

import dto.LoginRequest;
import dto.Response;
import dto.SignUpRequest;
import dto.UserResponse;
import entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import support.Authorization;
import support.CurrentUser;
import support.VerifyCodeUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("user/")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Response signUp(@RequestBody SignUpRequest request, HttpSession session) {
        if (session.getAttribute("_code") != null && session.getAttribute("_code").equals(request.getCheckCode())) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setEnabled(true);
            user.setRoot(false);
            if (userService.signUp(user)) {
                return new Response<>(200, "注册成功");
            }
            return new Response<>(500, "注册失败");
        } else {
            return new Response<>(501, "验证码错误");
        }
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public Response login(@RequestBody LoginRequest request, HttpSession session) {
        if (session.getAttribute("_code") != null && session.getAttribute("_code").equals(request.getCheckCode())) {

            UserResponse user = userService.login(request.getEmail(), request.getPassword());
            if (user != null) {
                return new Response<>(200, user);
            }
            return new Response<>(500, "用户不存在或密码错误");
        } else {
            return new Response<>(501, "验证码错误");
        }
    }

    @RequestMapping(value = "auth", method = RequestMethod.GET)
    @Authorization
    public Response login(@CurrentUser User user) {
        return new Response<>(200, modelMapper.map(user, UserResponse.class));
    }


    @RequestMapping(value = "authCode", method = RequestMethod.GET)
    public void getAuthCode(HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String verifyCode = RandomStringUtils.randomNumeric(4);
        session.setAttribute("_code", verifyCode);
        VerifyCodeUtils.outputImage(80, 50, response.getOutputStream(), verifyCode);
    }
}
