package dao.impl;

import dao.AbstractJpaDao;
import dao.BannerDAO;
import entity.Banner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Repository
public class BannerDAOImpl extends AbstractJpaDao<Banner> implements BannerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BannerDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Banner.class);
        this.sessionFactory = sessionFactory;
    }
}
