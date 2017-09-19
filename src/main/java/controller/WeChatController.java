package controller;

import dto.Response;
import entity.ApplicationForm;
import entity.User;
import job.AccessTokenJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.JoinService;
import service.UserService;
import service.WechatService;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.util.XMLConverUtil;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-7-21.
 */
@RestController
@RequestMapping("wechat/")
public class WeChatController {
    private final WechatService wechatService;
    private final JoinService joinService;
    private final UserService userService;

    @Autowired
    public WeChatController(WechatService wechatService, JoinService joinService, UserService userService) {
        this.wechatService = wechatService;
        this.joinService = joinService;
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
                    return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "非常感谢您关注isdc公众订阅号“进退之间”。。社团官网：https://www.scuisdc.org ，社团微博：http://weibo.com/scuisdc。同时您可以直接输入任何意见、建议或者问题，我们将会在一天之内给您回复！再次感谢您的关注！回复【面试】获取面试结果").toXML();
                }
                if (eventMessage.getContent().contains("报名")) {
                    return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "报名时间已经截止了哟，非常抱歉！").toXML();
                } else if (eventMessage.getContent().contains("面试")) {
                    ApplicationForm form = joinService.queryForm(eventMessage.getFromUserName()).orElse(null);
                    String message = form == null ? "很抱歉我们并没有收到您的报名表！" : form.getPass() ? form.getName() + "你好！恭喜你通过了ISDC的面试，成为ISDC5.0的一员，希望接下来的大学四年我们可以相处愉快！一定要坚持来听课哦" : form.getName() + "你好！非常遗憾的是，我们没能在面试通过的名单里找到你=v=不管怎么样，还是希望你能坚持参与我们的活动，给我们一个大惊喜！";
                    message += "ISDC新学期的第一次活动，萌新见面会将于9月23日（本周六）19点在江安校区二基楼B509教室开展=v=\n本次活动主要内容是大家互相认识一刚！同时也会讲一些诸如科学上网啊、代码规范啊、如何优雅省力的提问啊之类的问题！\n不管有没有通过面试，我们都希望能在周六晚上看到你的身影！\n这场面试并不能代表什么，对于通过面试的同学，这仅仅是一个小阶段的成功，要想有所发展还需继续努力；而对于那些惨遭滑铁卢的同学，暂时的失败并不是对你们今后的否定，你依然可以参加我们的每一次活动、授课，分享社团的学习资源，不断提高，证明自己。无关乎基础与天赋，只在乎你是否一往无前！";
                    return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), message).toXML();
                }
                return new XMLTextMessage(eventMessage.getFromUserName(), eventMessage.getToUserName(), "我们已经收到了您的消息，将会在24小时内作出回复。回复【面试】获取面试结果").toXML();
            }
        }
        return "";
    }

    @RequestMapping("token")
    public String getToken() {
        return AccessTokenJob.access_token;
    }

    @GetMapping("form")
    public Response<List<ApplicationForm>> listAllForms(@CookieValue(value = "accessToken") String accessToken) {
        User user = userService.auth(accessToken);
        if (user != null && user.getRoot()) {
            return new Response<>(200, joinService.queryAll());
        } else {
            return new Response<>(403);
        }
    }

    @PutMapping("form")
    public Response<Void> updateForm(@CookieValue(value = "accessToken") String accessToken, @RequestBody ApplicationForm form) {
        User user = userService.auth(accessToken);
        if (user != null && user.getRoot()) {
            form.setInterviewer(user.getUserName());
            joinService.submit(form);
            return new Response<>(200);
        } else {
            return new Response<>(403);
        }
    }
}
