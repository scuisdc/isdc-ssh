package service;

import entity.Banner;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
public interface BannerService {

    List<Banner> findAll();

    void save(Banner banner);

    void update(Banner banner);

    void delete(Banner banner);
}
