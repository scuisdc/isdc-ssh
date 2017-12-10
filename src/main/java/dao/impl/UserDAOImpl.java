package dao.impl;

import dao.AbstractJpaDao;
import dao.UserDAO;
import entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class UserDAOImpl extends AbstractJpaDao<User> implements UserDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByEmail(String email) {

        String hql = "from User u where u.email=? and u.enabled=true";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, email);

        return (User) query.uniqueResult();
    }

    @Override
    public User getUserByToken(String accessToken) {
        String hql = "from User u where u.accessToken=? and u.enabled=true";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, accessToken);

        return (User) query.uniqueResult();
    }
}
