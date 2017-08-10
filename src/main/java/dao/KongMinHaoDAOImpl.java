package dao;

import controller.ServiceController;
import entity.Asset;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
@Repository
public class KongMinHaoDAOImpl implements KongMinHaoDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public KongMinHaoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void increaseAsset(String name,long money) {


        Asset A = getAssetByName(name);
        A.setMoney(A.getMoney()+100);
        //String hql = "update Asset  set money=200 where name=\"KongMinHao\""
        this.sessionFactory.getCurrentSession().update(A);
        ///*.setParameter(0,A.getMoney()+money)*/.setParameter(0,A.getName());
    }

    @Override
    public Asset getAssetByName(String name) {
        String hql = "from Asset a where a.name=? ";

        return (Asset) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0,name).uniqueResult();
    }

}
