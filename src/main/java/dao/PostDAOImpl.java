package dao;

import entity.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public Map getPostById(int postId) {
        return (Map) sessionFactory.getCurrentSession().createQuery("select new map(id as id,title as title,createDate as createDate,lastModified as lastModified,preview as preview,author.email as email,author.userName as userName,content as content)from Post where id=?").setParameter(0, postId).uniqueResult();
    }

    @Override
    public List<Map> getAllPost(int page, int pageSize) {
//        return (List<Map>) sessionFactory.getCurrentSession().createQuery("select new Map(id as id,title as title,createDate as createDate,lastModified as lastModified,preview as preview,author.email as email,author.userName as userName) from Post order by lastModified desc ").setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();

        return (List<Map>) sessionFactory.getCurrentSession().createQuery("select new Map(id as id,title as title,createDate as createDate,lastModified as lastModified,preview as preview,author.email as email,author.userName as userName) from Post order by lastModified desc ").list();
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

    @Override
    public Post getFullPostById(int postId) {
        return (Post) sessionFactory.getCurrentSession().createQuery("from Post where id=?").setParameter(0, postId).uniqueResult();
    }

}
