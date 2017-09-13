package controller;

import job.AccessTokenJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.JoinService;
import service.WechatService;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLNewsMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.XMLConverUtil;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("wechat/")
public class WeChatController {
    private final WechatService wechatService;
    private final JoinService joinService;

    @Autowired
    public WeChatController(WechatService wechatService, JoinService joinService) {
        this.wechatService = wechatService;
        this.joinService = joinService;
    }

    @RequestMapping("notify")
    public String onMessageReceived(@RequestBody(required = false) String data, @RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam(value = "echostr", required = false) String echostr) {
        if (wechatService.checkSignature(timestamp, nonce, signature)) {
            if (echostr != null)
                return echostr;
            if (data != null) {
                EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, data);
                if (eventMessage.getEvent() != null && eventMessage.getEvent().equals("subscribe")) {
                    return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "非常感谢您关注isdc公众订阅号“进退之间”。。社团官网：https://www.scuisdc.org ，社团微博：http://weibo.com/scuisdc。同时您可以直接输入任何意见、建议或者问题，我们将会在一天之内给您回复！再次感谢您的关注！回复【报名】开始填写报名表！报名成功后回复【面试】获取后续面试安排").toXML();
                }
                if (eventMessage.getContent().contains("报名")) {
                    String openid = eventMessage.getFromUserName();
                    wechatService.saveOpenid(openid);
                    XMLNewsMessage.Article t = new XMLNewsMessage.Article();
                    t.setDescription("欢迎你的加入！");
                    t.setPicurl("http://7xq5uu.com1.z0.glb.clouddn.com/images_want.jpg");
                    t.setTitle("ISDC招新报名表");
                    t.setUrl("https://www.scuisdc.org/api/join/" + eventMessage.getFromUserName());
                    XMLNewsMessage xmlNewsMessage = new XMLNewsMessage(
                            eventMessage.getFromUserName(),
                            eventMessage.getToUserName(),
                            Collections.singletonList(
                                    t));
                    return xmlNewsMessage.toXML();
                } else if (eventMessage.getContent().contains("面试")) {
                    Date date = joinService.generateInterviewDate(eventMessage.getFromUserName(), false);
                    return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), date == null ? "查询不到您的面试安排，请稍后再试！" : String.format("您的面试时间是：%s，面试地点为二基楼B座405教室，请准时到场！时间安排如有冲突请私戳管理员", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date))).toXML();
                }
                return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "我们已经收到了您的消息，将会在24小时内作出回复。回复【报名】开始填写报名表，报名成功后回复【面试】获取后续面试安排").toXML();
            }
        }
        return "";
    }

    @RequestMapping("token")
    public String getToken() {
        return AccessTokenJob.access_token;
    }
}
