package dao.impl;

import dao.AbstractJpaDao;
import dao.SemesterDAO;
import entity.Semester;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Repository
public class SemesterDAOImpl extends AbstractJpaDao<Semester> implements SemesterDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public SemesterDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Semester.class);
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Semester findSemesterByDate(Date date) {
        return (Semester) sessionFactory.getCurrentSession().createQuery("from Semester where semesterStart< ? and semesterEnd > ?").setParameter(0, date).setParameter(1, date).uniqueResult();
    }
}
