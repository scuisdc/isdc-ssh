package dao.impl;

import dao.CommentDAO;
import entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
@Repository
public class CommentDAOImpl implements CommentDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CommentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Comment getCommentById(int commentId) {
        return (Comment) sessionFactory.getCurrentSession().createQuery("from Comment where id=?").setParameter(0, commentId).uniqueResult();
    }

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().persist(comment);
    }

    @Override
    public boolean delComment(int commentId) {
        return sessionFactory.getCurrentSession().createQuery("delete from Comment where id=?").setParameter(0, commentId).executeUpdate() == 1;
    }

    @Override
    public List<Map> getCommentByPost(int postId) {
        return sessionFactory.getCurrentSession().createQuery("select new map(id as id,content as content,sender.userName as userName,sender.email as email,commentDate as commentDate) from Comment where post.id=?").setParameter(0, postId).list();
    }
}
