package dao;

import org.hibernate.SessionFactory;

import java.util.List;

public abstract class AbstractJpaDao<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;

    public AbstractJpaDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public T findOne(int id) {
        return (T) sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName() + " where id=?").setParameter(0, id).uniqueResult();
    }

    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    public void save(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public T update(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    public void deleteById(int entityId) {
        sessionFactory.getCurrentSession().delete(findOne(entityId));
    }
}
