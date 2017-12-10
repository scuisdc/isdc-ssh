package dao.impl;

import dao.AbstractJpaDao;
import dao.CategoryDAO;
import entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Repository
public class CategoryDAOImpl extends AbstractJpaDao<Category> implements CategoryDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Category.class);
        this.sessionFactory = sessionFactory;
    }
}
