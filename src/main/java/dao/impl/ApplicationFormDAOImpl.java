package dao.impl;

import dao.ApplicationFormDAO;
import entity.ApplicationForm;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class ApplicationFormDAOImpl implements ApplicationFormDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ApplicationFormDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<ApplicationForm> getAllForms() {
        String hql = "from ApplicationForm where interview is not null order by interview";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void addForm(ApplicationForm form) {
        sessionFactory.getCurrentSession().persist(form);
    }

    @Override
    public void updateForm(ApplicationForm form) {
        sessionFactory.getCurrentSession().update(form);
    }

    @Override
    public Optional<ApplicationForm> queryByOpenid(String openid) {
        return sessionFactory.getCurrentSession().createQuery("from ApplicationForm where openid=?").setParameter(0, openid).uniqueResultOptional();
    }

    @Override
    public Date getLatestDate() {
        return (Date) sessionFactory.getCurrentSession().createQuery("select max(interview) from ApplicationForm").uniqueResultOptional().orElseGet(() -> new Date(1505523600000L));
    }

    @Override
    public long queryConcurrentDateCount(Date latestDate) {
        return (long) sessionFactory.getCurrentSession().createQuery("select count(*) from ApplicationForm where interview=?").setParameter(0, latestDate).uniqueResult();
    }
}
