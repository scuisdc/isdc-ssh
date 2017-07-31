package dao;

import entity.User;
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
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
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

    @Override
    public List<User> getAllUser() {

        String hql = "from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);

        return query.list();
    }

    @Override
    public void addUser(User user) {
        user.generateToken();
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public boolean delUser(String id) {

        String hql = "delete User u where u.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);

        return (query.executeUpdate() > 0);
    }

    @Override
    public boolean updateUser(User user) {
        throw new NotImplementedException();
    }
}
