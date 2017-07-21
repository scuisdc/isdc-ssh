package Controller;

import DTO.LoginRequest;
import DTO.Response;
import DTO.SignUpRequest;
import Entity.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response signUp(@RequestBody SignUpRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setEnabled(true);
        if (userService.signUp(user)) {
            return new Response<>(200);
        }
        return new Response<>(500, "注册失败");
    }

    @RequestMapping(value = "auth/{accessToken}", method = RequestMethod.GET)
    public Response login(@PathVariable("accessToken") String accessToken) {
        User user = userService.auth(accessToken);
        if (user != null) {
            return new Response<>(200, user);
        }
        return new Response<>(500, "登录已过期，请重新登录");
    }


    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public Response login(@RequestBody LoginRequest request) {
        User user = userService.login(request.getEmail(), request.getPassword());
        if (user != null) {
            return new Response<>(200, user);
        }
        return new Response<>(500, "用户不存在或密码错误");
    }

}
