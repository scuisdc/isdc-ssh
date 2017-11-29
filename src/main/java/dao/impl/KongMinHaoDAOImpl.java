package dao.impl;


import dao.KongMinHaoDAO;
import entity.Asset;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KongMinHaoDAOImpl implements KongMinHaoDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public KongMinHaoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void increaseAsset(String name,long money) {


        Asset A = getAssetByName(name);
        A.setMoney(A.getMoney()+money);
        this.sessionFactory.getCurrentSession().update(A);

    }

    @Override
    public Asset getAssetByName(String name) {
        String hql = "from Asset a where a.name =?";
        Asset result = (Asset) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0,name).uniqueResult();
        if(result!=null){
            return result;
        }
        else{
            result = new Asset(name,0L);
            addAsset(result);
            return result;
        }
    }

    @Override
    public void addAsset(Asset asset) {
        this.sessionFactory.getCurrentSession().persist(asset);
    }

    @Override
    public List<Asset> getAllAsset() {
        String hql = "from Asset order by money desc ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setMaxResults(10);
        return query.list();
    }
}

