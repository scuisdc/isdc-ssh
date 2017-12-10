package controller;

import job.AccessTokenJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
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
    private final UserService userService;

    @Autowired
    public WeChatController(WechatService wechatService, UserService userService) {
        this.wechatService = wechatService;
        this.userService = userService;
    }

    @RequestMapping("notify")
    public String onMessageReceived(@RequestBody(required = false) String data, @RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam(value = "echostr", required = false) String echostr) {
        if (wechatService.checkSignature(timestamp, nonce, signature)) {
            if (echostr != null)
                return echostr;
            if (data != null) {
                EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, data);
                if (eventMessage.getEvent() != null && eventMessage.getEvent().equals("subscribe")) {
                    return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "非常感谢您关注isdc公众订阅号“进退之间”。。社团官网：https://www.scuisdc.org ，社团微博：http://weibo.com/scuisdc。同时您可以直接输入任何意见、建议或者问题，我们将会在一天之内给您回复！再次感谢您的关注！").toXML();
                }
                return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "我们已经收到了您的消息，将会在24小时内作出回复。").toXML();
            }
        }
        return "";
    }

    @RequestMapping("token")
    public String getToken() {
        return AccessTokenJob.access_token;
    }
}
