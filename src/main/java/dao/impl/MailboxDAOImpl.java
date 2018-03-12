package dao.impl;

import dao.AbstractJpaDao;
import dao.MailboxDAO;
import entity.Mailbox;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class MailboxDAOImpl extends AbstractJpaDao<Mailbox> implements MailboxDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public MailboxDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Mailbox.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Mailbox> findByUser(Integer id) {
        return sessionFactory.getCurrentSession().createQuery("from Mailbox where user.id=?").setParameter(0, id).list();
    }
}
