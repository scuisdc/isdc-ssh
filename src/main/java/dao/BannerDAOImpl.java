package dao;

import entity.Banner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-24.
 */
@Repository
public class BannerDAOImpl implements BannerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BannerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Banner> listAllBanner() {
        return sessionFactory.getCurrentSession().createQuery("from Banner order by piority desc ").list();
    }

    @Override
    public void addBanner(Banner banner) {
        sessionFactory.getCurrentSession().persist(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        sessionFactory.getCurrentSession().update(banner);
    }

    @Override
    public boolean deleteBanner(Banner banner) {
        return sessionFactory.getCurrentSession().createQuery("delete from Banner where id=?").setParameter(0, banner.getId()).executeUpdate() > 0;
    }

    @Override
    public Banner findBannerByTitle(String title) {
        return (Banner) sessionFactory.getCurrentSession().createQuery("from Banner where title=?").setParameter(0, title).uniqueResult();
    }
}
