package controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dto.AssetRequest;
import dto.CalScoreRequest;
import dto.Response;
import entity.Asset;
import entity.ScoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.JWCService;
import service.KongMinHaoService;
import service.KongMinHaoServicelmpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("service/")
public class ServiceController {
    private final JWCService jwcService;
    private final KongMinHaoService kongMinHaoService;

    @Autowired
    public ServiceController(JWCService jwcService, KongMinHaoService kongMinHaoService) {
        this.jwcService = jwcService;
        this.kongMinHaoService = kongMinHaoService;
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

    @RequestMapping(value = "/KongMinHao/increase", method = RequestMethod.POST)
    public Response increaseAssets(@RequestBody AssetRequest request) {
        //ScoreRequest scoreRequest = jwcService.findRequest(request.getZjh(), request.getMm(), request.getDate());
        Asset asset = new Asset(request.getName(), request.getMoney());
        kongMinHaoService.increaseAsset(asset);
        return new Response<>(200, "添加资产成功");
    }

    @RequestMapping(value = "/KongMinHao/getAsset", method = RequestMethod.POST)
    public Response getAssets(@RequestBody AssetRequest request) {
        Asset asset = new Asset(request.getName(), request.getMoney());
        Asset assetRequest = kongMinHaoService.getAsset(asset);

        if (assetRequest != null) {
            return new Response(200, assetRequest);
        }
        return new Response<>(500, "获取资产失败");
    }


    @RequestMapping(value = "/KongMinHao/getRank", method = RequestMethod.POST)
    public Response getRank() {
        List<Asset> Rank = this.kongMinHaoService.getRank();

        if (Rank != null) {
            return new Response(200, Rank);
        }
        return new Response<>(500, "获取排行榜失败");
    }
}
