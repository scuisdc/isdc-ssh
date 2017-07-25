package DAO;

import Entity.ScoreRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Repository
public class ScoreRequestDAOImpl implements ScoreRequestDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public ScoreRequestDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRequest(ScoreRequest request) {
        this.sessionFactory.getCurrentSession().persist(request);
    }

    @Override
    public void updateRequest(ScoreRequest request) {
        this.sessionFactory.getCurrentSession().update(request);
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
