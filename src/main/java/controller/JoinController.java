package controller;

import entity.ApplicationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.JoinService;

import java.util.Date;
import java.util.Optional;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-10.
 */

@Controller
@RequestMapping("join/{openid}")
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping
    public String join(@PathVariable String openid) {
        Optional<ApplicationForm> applicationForm = joinService.queryForm(openid);
        if (applicationForm.isPresent() && applicationForm.get().getName() != null) {
            return "success";
        }
        return "index";
    }

    @PostMapping
    public String submit(@PathVariable String openid, @RequestParam("name") String name, @RequestParam("stuId") Long stuId, @RequestParam("gender") String gender, @RequestParam("nationality") String nationality, @RequestParam("tel") Long tel, @RequestParam("email") String email, @RequestParam("introduce") String introduce, @RequestParam("description") String description) {
        Optional<ApplicationForm> optional = joinService.queryForm(openid);
        if (optional.isPresent() && optional.get().getName() == null) {
            ApplicationForm applicationForm = optional.get();
            applicationForm.setOpenid(openid);
            applicationForm.setDescription(description);
            applicationForm.setName(name);
            applicationForm.setIntroduce(introduce);
            applicationForm.setTel(tel);
            applicationForm.setEmail(email);
            applicationForm.setCreateDate(new Date());
            applicationForm.setStuId(stuId);
            applicationForm.setGender(gender);
            applicationForm.setNationality(nationality);
            joinService.submit(applicationForm);
        }
        return "success";
    }
}
