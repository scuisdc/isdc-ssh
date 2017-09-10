package controller;

import job.AccessTokenJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.WechatService;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLNewsMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.XMLConverUtil;

import java.util.Collections;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("wechat/")
public class WechatController {
    private final WechatService wechatService;

    @Autowired
    public WechatController(WechatService wechatService) {
        this.wechatService = wechatService;
    }

    @GetMapping("notify")
    public String onValidate(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam(value = "echostr", required = false) String echostr) {
        if (wechatService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "";
    }

    @PostMapping("notify")
    public String onMessageReceived(@RequestBody String data) {
        EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, data);
        if (eventMessage.getContent().contains("报名")) {
            XMLNewsMessage.Article t = new XMLNewsMessage.Article();
            t.setDescription("欢迎你的加入！");
            t.setPicurl("https://scuisdc.org/assets/logo_dark.png");
            t.setTitle("ISDC招新报名表");
            t.setUrl("https://scuisdc.org/api/join/" + eventMessage.getFromUserName());
            XMLNewsMessage xmlNewsMessage = new XMLNewsMessage(
                    eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    Collections.singletonList(
                            t));
            return xmlNewsMessage.toXML();
        }
        return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "我们已经收到了你的信息，会尽快给您回复！").toXML();
    }

    @RequestMapping("token")
    public String getToken() {
        return AccessTokenJob.access_token;
    }
}
