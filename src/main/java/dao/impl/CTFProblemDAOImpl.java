package dao.impl;

import dao.CTFProblemDAO;
import entity.CTFProblem;
import org.hibernate.SessionFactory;
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
        String hql = "";

        return null;
    }

    @Override
    public List<CTFProblem> getCTFProblemsByName(String name) {
        return null;
    }

    @Override
    public List<CTFProblem> getCTFProblemByID(int id) {
        return null;
    }

    @Override
    public void deleteCTFProblem() {

    }

    @Override
    public void UpdateCTFProblem(CTFProblem ctfProblem) {

    }

    @Override
    public void addCTFProblem(CTFProblem ctfProblem) {

    }
}
