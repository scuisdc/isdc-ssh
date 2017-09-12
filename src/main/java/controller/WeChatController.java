package controller;

import job.AccessTokenJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.WechatService;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.XMLConverUtil;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("wechat/")
public class WeChatController {
    private final WechatService wechatService;

    @Autowired
    public WeChatController(WechatService wechatService) {
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
        if (eventMessage.getEvent() != null && eventMessage.getEvent().equals("subscribe")) {
            return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "非常感谢您关注isdc公众订阅号“进退之间”。。社团官网：https://www.scuisdc.org ，社团微博：http://weibo.com/scuisdc。同时您可以直接输入任何意见、建议或者问题，我们将会在一天之内给您回复！再次感谢您的关注！回复【报名】开始填写报名表！报名成功后回复【面试】获取后续面试安排").toXML();
        }
        if (eventMessage.getContent().contains("报名")) {
//            XMLNewsMessage.Article t = new XMLNewsMessage.Article();
//            t.setDescription("欢迎你的加入！");
//            t.setPicurl("http://7xq5uu.com1.z0.glb.clouddn.com/images_want.jpg");
//            t.setTitle("ISDC招新报名表");
//            t.setUrl("https://www.scuisdc.org/api/join/" + eventMessage.getFromUserName());
//            XMLNewsMessage xmlNewsMessage = new XMLNewsMessage(
//                    eventMessage.getFromUserName(),
//                    eventMessage.getToUserName(),
//                    Collections.singletonList(
//                            t));
//            return xmlNewsMessage.toXML();
            return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "报名接口正在维护中！请稍后再试").toXML();
        } else if (eventMessage.getContent().contains("面试")) {
            return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "").toXML();
        }
        return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "非常感谢您关注isdc公众订阅号“进退之间”。。社团官网：https://www.scuisdc.org ，社团微博：http://weibo.com/scuisdc。同时您可以直接输入任何意见、建议或者问题，我们将会在一天之内给您回复！再次感谢您的关注！回复【报名】开始填写报名表！报名成功后回复【面试】获取后续面试安排").toXML();
    }

    @RequestMapping("token")
    public String getToken() {
        return AccessTokenJob.access_token;
    }
}
