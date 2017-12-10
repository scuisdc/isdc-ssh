package service.impl;

import dao.AnnounceDAO;
import entity.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnnounceService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
@Service("AnnounceService")
@Transactional
public class AnnounceServiceImpl implements AnnounceService {

    private final AnnounceDAO announceDAO;


    @Autowired
    public AnnounceServiceImpl(AnnounceDAO announceDAO) {
        this.announceDAO = announceDAO;
    }

    @Override
    public List<Announce> listAll() {
        return announceDAO.findAll();
    }

}
