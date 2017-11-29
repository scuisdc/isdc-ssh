package dao.impl;

import dao.CTFProblemDAO;
import entity.CTFFlagGetter;
import entity.CTFProblem;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WaterMelon on 2017/11/8.
 */
@Repository
public class CTFProblemDAOImpl implements CTFProblemDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public CTFProblemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CTFProblem> getAllCTFProblems() {
        String hql = "from CTFProblem c order by c.time desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<CTFProblem> getCTFProblemsByName(String name) {
        String hql = "from CTFProblem c where c.name like ? ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,"%"+name+"%");
        return query.list();
    }

    @Override
    public List<CTFFlagGetter> getAllCTFFlagGetter() {
        String hql = "from CTFFlagGetter c order by c.time desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<CTFFlagGetter> getCTFFlagGetterByID(int problemID) {
        String hql = "from CTFFlagGetter c where c.problemid = ? order by c.time desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,problemID);
        return query.list();
    }

    @Override
    public CTFProblem getCTFProblemByID(int problemID) {
        String hql = "from CTFProblem c where c.id = ? ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,problemID);
        return (CTFProblem) query.uniqueResult();
    }

    @Override
    public boolean deleteCTFProblem(int id) {
        String hql = "delete from CTFProblem c where c.id =:id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        return (query.executeUpdate() > 0);
    }
    @Override
    public void updateCTFProblem(CTFProblem ctfProblem) {
        sessionFactory.getCurrentSession().update(ctfProblem);
    }

    @Override
    public void addCTFProblem(CTFProblem ctfProblem) {
        sessionFactory.getCurrentSession().persist(ctfProblem);
    }

    @Override
    public void addCTFFlagGetter(CTFFlagGetter ctfFlagGetter) {
        sessionFactory.getCurrentSession().persist(ctfFlagGetter);
    }

    @Override
    public void deleteCTFFlagGetter(int problemID,String userEmail) {
        String hql = "delete from CTFFlagGetter c where c.problemid = ? and c.userEmail = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,problemID);
        query.setParameter(1,userEmail);
    }


}
