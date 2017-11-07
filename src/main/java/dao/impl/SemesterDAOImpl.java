package dao.impl;

import dao.SemesterDAO;
import entity.Semester;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Repository
public class SemesterDAOImpl implements SemesterDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public SemesterDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Semester> listAllSemester() {
        return sessionFactory.getCurrentSession().createQuery("from Semester ").list();
    }

    @Override
    public void addSemester(Semester semester) {
        sessionFactory.getCurrentSession().persist(semester);
    }

    @Override
    public void updateSemester(Semester semester) {
        sessionFactory.getCurrentSession().update(semester);
    }

    @Override
    public boolean deleteSemester(Semester semester) {
        return sessionFactory.getCurrentSession().createQuery("delete from Semester where Semester.id = ?").setParameter(0, semester.getId()).executeUpdate() > 0;
    }

    @Override
    public Semester findSemesterByDate(Date date) {
        return (Semester) sessionFactory.getCurrentSession().createQuery("from Semester where semesterStart< ? and semesterEnd > ?").setParameter(0, date).setParameter(1, date).uniqueResult();
    }
}
