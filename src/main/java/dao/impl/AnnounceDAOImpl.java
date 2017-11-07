package dao.impl;

import dao.AnnounceDAO;
import entity.Announce;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class AnnounceDAOImpl implements AnnounceDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public AnnounceDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Announce getAnnounceByTitle(String title) {
        throw new NotImplementedException();

    }

    @Override
    public List<Announce> getAllAnnounce() {
        String hql = "from Announce order by createDate desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void addAnnounce(Announce announce) {
        throw new NotImplementedException();

    }

    @Override
    public boolean delAnnounce(String title) {
        throw new NotImplementedException();

    }

    @Override
    public boolean updateAnnounce(Announce announce) {
        throw new NotImplementedException();
    }
}
