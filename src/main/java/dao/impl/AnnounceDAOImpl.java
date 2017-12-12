package dao.impl;

import dao.AbstractJpaDao;
import dao.AnnounceDAO;
import entity.Announce;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class AnnounceDAOImpl extends AbstractJpaDao<Announce> implements AnnounceDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public AnnounceDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Announce.class);
        this.sessionFactory = sessionFactory;
    }
}
