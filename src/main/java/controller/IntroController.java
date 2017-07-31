package controller;

import dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.AnnounceService;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("intro/")
public class IntroController {
    private final AnnounceService announceService;

    @Autowired
    public IntroController(AnnounceService announceService) {
        this.announceService = announceService;
    }

    @RequestMapping(value = "announce", method = RequestMethod.GET)
    public Response listAnnounce() {
        return new Response<>(200, announceService.listAll());
    }

    @RequestMapping(value = "suggest", method = RequestMethod.POST)
    public Response suggest() {
        return new Response<>(500, "Not implemented yet");
    }

}
