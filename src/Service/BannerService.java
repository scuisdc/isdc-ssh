package Service;

import Entity.Banner;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-21.
 */
public interface BannerService {

    List<Banner> listAll();

    void addBanner(Banner banner);

    void updateBanner(Banner banner);

    boolean deleteBanner(Banner banner);

    Banner findBannerByTitle(String title);
}
