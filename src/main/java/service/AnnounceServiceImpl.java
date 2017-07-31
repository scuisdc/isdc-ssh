package service;

import dao.AnnounceDAO;
import entity.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Service("AnnounceService")
@Transactional
public class AnnounceServiceImpl implements AnnounceService {

    private final AnnounceDAO userDAO;


    @Autowired
    public AnnounceServiceImpl(AnnounceDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<Announce> listAll() {
        return userDAO.getAllAnnounce();
    }

}
