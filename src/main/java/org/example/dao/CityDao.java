package org.example.dao;
import org.example.config.SessionFactoryProvider;
import org.example.entity.City;
import org.example.exception.CityNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CityDao implements DaoOperation<City, Integer> {
    private static final CityDao INSTANCE = new CityDao();
    private org.hibernate.SessionFactory factory = SessionFactoryProvider.getSessionFactory();

    public CityDao() {
    }

    public Optional<City> findByName(String name) {

      try (Session session = factory.openSession()){
        Query query = session.createQuery("FROM City c WHERE name = :name");
        query.setParameter("name", name);
        City city = (City) query.uniqueResult();
        if (city == null) {
            throw new CityNotFoundException("City with name: " + name + " not found.");
        }
        return Optional.of(city);
    } catch(
    Exception e)

    {
        return Optional.empty();
    }

}



    public Long getTotalCount() {
        Session session = factory.openSession();
        Query query = session.createQuery("SELECT count(c) FROM City c", City.class);
        return (Long) query.uniqueResult();

    }
    @Override
    public List<City> findAll() {
        Session session = factory.openSession();
        Query query = session.createQuery("from City", City.class);
        return query.getResultList();
    }

    @Override
//    public Optional<City> findById(Integer id) {
//        Session session = factory.openSession();
//        Query<City> query = session.createQuery("SELECT c FROM City c WHERE c.id = :id", City.class);
//        query.setParameter("id", id);
//        City city = query.uniqueResult();
//        if (city == null) {
//            throw new CityNotFoundException("City with id: " + id + " not found.");
//
//        } else {
//
//
//            return Optional.ofNullable(city);
//
//        }
//    }
    public Optional<City> findById(Integer id) {
        try (Session session = factory.openSession()) {
            Query<City> query = session.createQuery("SELECT c FROM City c WHERE c.id = :id", City.class);
            query.setParameter("id", id);
            City city = query.uniqueResult();
            if (city == null) {
                throw new CityNotFoundException("City with id: " + id + " not found.");
            }
            return Optional.of(city);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(City city) {


    }

    @Override
    public void update(City city) {
    }

    @Override
    public void deletedById(Integer id) {


    }
    public static CityDao getInstance(){
        return INSTANCE;
    }
}
