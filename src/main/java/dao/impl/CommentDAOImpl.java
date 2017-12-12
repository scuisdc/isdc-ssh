package dao.impl;

import dao.AbstractJpaDao;
import dao.CommentDAO;
import entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
@Repository
public class CommentDAOImpl extends AbstractJpaDao<Comment> implements CommentDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CommentDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Comment.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Comment> getCommentByPost(int postId) {
        return sessionFactory.getCurrentSession().createQuery("from Comment where post.id=?").setParameter(0, postId).list();
    }
}
