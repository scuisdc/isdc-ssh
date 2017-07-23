package Controller;

import DTO.LoginRequest;
import DTO.Response;
import DTO.SignUpRequest;
import Entity.User;
import Service.UserService;
import Utils.VerifyCodeUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("user/")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Response signUp(@RequestBody SignUpRequest request, HttpSession session) {
        if (session.getAttribute("_code") != null && session.getAttribute("_code").equals(request.getCheckCode())) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setEnabled(true);
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
        System.out.println(session.getAttribute("_code"));
        System.out.println(request.getCheckCode());
        if (session.getAttribute("_code") != null && session.getAttribute("_code").equals(request.getCheckCode())) {

            User user = userService.login(request.getEmail(), request.getPassword());
            if (user != null) {
                return new Response<>(200, user);
            }
            return new Response<>(500, "用户不存在或密码错误");
        } else {
            return new Response<>(501, "验证码错误");
        }
    }

    @RequestMapping(value = "auth/{accessToken}", method = RequestMethod.GET)
    public Response login(@PathVariable("accessToken") String accessToken) {
        User user = userService.auth(accessToken);
        if (user != null) {
            return new Response<>(200, user);
        }
        return new Response<>(500, "登录已过期，请重新登录");
    }


    @RequestMapping(value = "authCode", method = RequestMethod.GET)
    public void getAuthCode(HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String verifyCode = RandomStringUtils.randomNumeric(4);
        session.setAttribute("_code", verifyCode);
        VerifyCodeUtils.outputImage(80, 50, response.getOutputStream(), verifyCode);
    }
}
