package org.example.dao;

import org.example.config.SessionFactoryProvider;
import org.example.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CityDao implements DaoOperation<City, Long> {
    private static final CityDao INSTANCE = new CityDao();

    public SessionFactory getFactory() {
        return factory;
    }

    private CityDao() {
    }

    private SessionFactory factory = SessionFactoryProvider.getSessionFactory();
    public List<City> getItems(int offset, int limit) {
        Query<City> query = factory.getCurrentSession().createQuery("select c from City c", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount() {
        Query<Long> query = factory.getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }
    @Override
    public List<City> findAll() {
        Session session = factory.openSession();
        Query query = session.createQuery("from City");
        return query.getResultList();
    }

    @Override
    public Optional<City> findById(Long id) {
        Session session = factory.openSession();
        Query query = session.createQuery("FROM City WHERE id = :id");
        query.setParameter("id", id);
        return (Optional<City>) query.uniqueResult();
    }

    @Override
    public void save(City city) {

    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deletedById(Long aLong) {

    }
    public static CityDao getInstance(){
        return INSTANCE;
    }
}
