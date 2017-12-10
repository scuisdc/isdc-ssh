package dao.impl;

import dao.AbstractJpaDao;
import dao.PostDAO;
import entity.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Repository
public class PostDAOImpl extends AbstractJpaDao<Post> implements PostDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PostDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Post.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Post> getPostByEmail(String email) {
        return sessionFactory.getCurrentSession().createQuery("from Post where author.email=?").setParameter(0, email).list();
    }
}
