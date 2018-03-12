package dao;


import entity.Mailbox;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface MailboxDAO extends IGenericDao<Mailbox> {
    List<Mailbox> findByUser(Integer id);
}
