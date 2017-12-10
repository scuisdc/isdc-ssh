package dao.impl;

import dao.AbstractJpaDao;
import dao.ScoreRequestDAO;
import entity.ScoreRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Repository
public class ScoreRequestDAOImpl extends AbstractJpaDao<ScoreRequest> implements ScoreRequestDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ScoreRequestDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, ScoreRequest.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ScoreRequest findRequest(String zjh, String mm, Date date) {
        return (ScoreRequest) this.sessionFactory.getCurrentSession().createQuery("from ScoreRequest where zjh=? and mm=? and date>? order by date desc")
                .setParameter(0, zjh)
                .setParameter(1, mm)
                .setParameter(2, date)
                .setMaxResults(1).uniqueResult();
    }
}
