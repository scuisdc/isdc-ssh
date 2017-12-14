package dao.impl;

import dao.AbstractJpaDao;
import dao.MailFolderDAO;
import entity.MailFolder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Repository
public class MailFolderDAOImpl extends AbstractJpaDao<MailFolder> implements MailFolderDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public MailFolderDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, MailFolder.class);
        this.sessionFactory = sessionFactory;
    }
}
