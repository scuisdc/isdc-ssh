package dao.impl;

import dao.AbstractJpaDao;
import dao.CTFProblemDAO;
import entity.CTFProblem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by WaterMelon on 2017/11/8.
 */
@Repository
public class CTFProblemDAOImpl extends AbstractJpaDao<CTFProblem> implements CTFProblemDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public CTFProblemDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, CTFProblem.class);
        this.sessionFactory = sessionFactory;
    }
}
