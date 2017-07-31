package dao;

import entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAllCategory() {
        return sessionFactory.getCurrentSession().createQuery("from Category ").list();
    }

    @Override
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().persist(category);
    }

}
