package service.impl;

import dao.BannerDAO;
import entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BannerService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Service("BannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

    private final BannerDAO bannerDAO;

    @Autowired
    public BannerServiceImpl(BannerDAO bannerDAO) {
        this.bannerDAO = bannerDAO;
    }

    @Override
    public List<Banner> listAll() {
        return bannerDAO.listAllBanner();
    }

    @Override
    public void addBanner(Banner banner) {
        bannerDAO.addBanner(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDAO.updateBanner(banner);
    }

    @Override
    public boolean deleteBanner(Banner banner) {
        return bannerDAO.deleteBanner(banner);
    }

    @Override
    public Banner findBannerByTitle(String title) {
        return bannerDAO.findBannerByTitle(title);
    }
}
