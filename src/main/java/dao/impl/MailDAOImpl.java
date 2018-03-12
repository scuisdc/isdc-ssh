package dao.impl;

import dao.AbstractJpaDao;
import dao.MailDAO;
import entity.Mail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class MailDAOImpl extends AbstractJpaDao<Mail> implements MailDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public MailDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Mail.class);
        this.sessionFactory = sessionFactory;
    }
}
