package DAO;

import Entity.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Repository
public class PostDAOImpl implements PostDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PostDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Post getPostById(int postId) {
        Post o = (Post) sessionFactory.getCurrentSession().createQuery("from Post where id=?").setParameter(0, postId).uniqueResult();
        if (o != null) {
            o.getAuthor().setAccessToken(null);
        }
        return o;
    }

    @Override
    public List<Post> getAllPost(int page, int pageSize) {
        List<Post> list = sessionFactory.getCurrentSession().createQuery("from Post ").setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
        list.forEach(item -> item.getAuthor().setAccessToken(null));
        return list;
    }

    @Override
    public void addPost(Post post) {
        sessionFactory.getCurrentSession().persist(post);
    }

    @Override
    public boolean delPost(int postId) {
        return sessionFactory.getCurrentSession().createQuery("delete from Post where id=?").setParameter(0, postId).executeUpdate() == 1;
    }

    @Override
    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
    }

}
