package DAO;

import Entity.Announce;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-22.
 */
public interface AnnounceDAO {
    Announce getAnnounceByTitle(String title);

    List<Announce> getAllAnnounce();

    void addAnnounce(Announce announce);

    boolean delAnnounce(String title);

    boolean updateAnnounce(Announce announce);
}
