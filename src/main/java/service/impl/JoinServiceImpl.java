package service.impl;

import dao.ApplicationFormDAO;
import entity.ApplicationForm;
import job.AccessTokenJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.JoinService;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.message.massmessage.MassTextMessage;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-10.
 */
@Service("JoinService")
@Transactional
public class JoinServiceImpl implements JoinService {
    private final ApplicationFormDAO applicationFormDAO;

    @Autowired
    public JoinServiceImpl(ApplicationFormDAO applicationFormDAO) {
        this.applicationFormDAO = applicationFormDAO;
    }

    @Override
    public void submit(ApplicationForm applicationForm) {
        applicationFormDAO.addForm(applicationForm);
        MassTextMessage massMessage = new MassTextMessage("报名成功！具体面试安排我们会在48小时内通过公众号发送到您的微信，请注意查收。");
        massMessage.setTouser(Collections.singleton(applicationForm.getOpenid()));
        MessageAPI.messageMassSend(AccessTokenJob.access_token, massMessage);
    }

    @Override
    public boolean submitted(String openid) {
        return applicationFormDAO.queryByOpenid(openid).isPresent();
    }
}
