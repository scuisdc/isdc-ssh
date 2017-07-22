package Service;

import DAO.AnnounceDAO;
import Entity.Announce;
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
public class AnnounceService {

    private final AnnounceDAO userDAO;


    @Autowired
    public AnnounceService(AnnounceDAO userDAO) {
        this.userDAO = userDAO;
    }


    public List<Announce> listAll() {
        return userDAO.getAllAnnounce();
    }

}
