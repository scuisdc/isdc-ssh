package service.impl;

import dao.ApplicationFormDAO;
import entity.ApplicationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.JoinService;

import javax.transaction.Transactional;

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
    }

    @Override
    public boolean submitted(String openid) {
        return applicationFormDAO.queryByOpenid(openid).isPresent();
    }
}
