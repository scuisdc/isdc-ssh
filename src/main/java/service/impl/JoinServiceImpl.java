package service.impl;

import dao.ApplicationFormDAO;
import entity.ApplicationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.JoinService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        applicationFormDAO.updateForm(applicationForm);
    }

    @Override
    public Optional<ApplicationForm> queryForm(String openid) {
        return applicationFormDAO.queryByOpenid(openid);
    }

    @Override
    public Date generateInterviewDate(String openid, boolean force) {

        Optional<ApplicationForm> optional = queryForm(openid);
        if (optional.isPresent()) {
            ApplicationForm form = optional.get();
            if (form.getName() != null && (force || form.getInterview() == null)) {
                Date latestDate = applicationFormDAO.getLatestDate();
                if (applicationFormDAO.queryConcurrentDateCount(latestDate) > 2) {
                    latestDate.setTime(latestDate.getTime() + 600000L);
                }
                if (latestDate.getHours() >= 12 && latestDate.getHours() < 14) {
                    latestDate.setHours(14);
                }
                if (latestDate.getHours() >= 18) {
                    latestDate.setHours(9);
                    latestDate.setDate(latestDate.getDate() + 1);
                }
                form.setInterview(latestDate);
            }
            return form.getInterview();
        }
        return null;
    }

    @Override
    public List<ApplicationForm> queryAll() {
        return applicationFormDAO.getAllForms();
    }
}
