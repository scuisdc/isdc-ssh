package controller;

import dto.CalScoreRequest;
import dto.Response;
import entity.ScoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.JWCService;

import java.io.IOException;
import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("service/")
public class ServiceController {
    private final JWCService jwcService;

    @Autowired
    public ServiceController(JWCService jwcService) {
        this.jwcService = jwcService;
    }

    @RequestMapping(value = "jwc/score", method = RequestMethod.POST)
    public Response calScore(@RequestBody CalScoreRequest request) throws IOException {
        ScoreRequest scoreRequest = new ScoreRequest(request.getZjh(), request.getMm());
        scoreRequest.setComplete(false);
        scoreRequest.setDate(new Date());
        jwcService.addRequest(scoreRequest);
        jwcService.getScore(scoreRequest);
        return new Response<>(200, "正在连接教务处……");
    }


    @RequestMapping(value = "jwc/score/result", method = RequestMethod.POST)
    public Response getScoreResult(@RequestBody CalScoreRequest request) {
        ScoreRequest scoreRequest = jwcService.findRequest(request.getZjh(), request.getMm(), request.getDate());
        if (scoreRequest != null) {
            return new Response(200, scoreRequest);
        }
        return new Response<>(500, "查询失败");
    }
}