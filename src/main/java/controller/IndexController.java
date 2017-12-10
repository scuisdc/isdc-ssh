package controller;

import dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.BannerService;
import service.SemesterService;

import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("")
public class IndexController {

    private final SemesterService semesterService;

    private final BannerService bannerService;

    @Autowired
    public IndexController(SemesterService semesterService, BannerService bannerService) {
        this.semesterService = semesterService;
        this.bannerService = bannerService;
    }

    @RequestMapping(value = "schedule", method = RequestMethod.GET)
    public Response listAnnounce() {
        return new Response<>(200, semesterService.findSemesterByDate(new Date(System.currentTimeMillis())));
    }

    @RequestMapping(value = "banner", method = RequestMethod.GET)
    public Response listBanner() {
        return new Response<>(200, bannerService.findAll());
    }

}
