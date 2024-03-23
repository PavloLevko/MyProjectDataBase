package org.example.dao;

import org.example.config.SessionFactoryProvider;
import org.example.entity.City;
import org.example.exception.CityNotFoundException;
import org.example.service.CityService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class CityDao implements DaoOperation<City, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityDao.class);
    private static final CityDao INSTANCE = new CityDao();
    private org.hibernate.SessionFactory factory = SessionFactoryProvider.getSessionFactory();

    public CityDao() {
    }

    public Optional<City> findByName(String name) {
        LOGGER.info("Method findByName was started.");
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM City c WHERE name = :name");
            query.setParameter("name", name);
            City city = (City) query.uniqueResult();
            return Optional.ofNullable(city);
        } catch (HibernateException e) {
            LOGGER.error("Error in method findByName" + e);
            return Optional.empty();
        }
    }


    public Long getTotalCount() {
        LOGGER.info("Method getTotalCount was started.");
        Session session = factory.openSession();
        Query query = session.createQuery("SELECT COUNT(c) FROM City c");
        return (Long) query.uniqueResult();
    }

    @Override
    public List<City> findAll() {
        LOGGER.info("Method findAll was started.");
        Session session = factory.openSession();
        Query query = session.createQuery("SELECT name FROM City");
        return query.getResultList();
    }

    @Override
    public Optional<City> findById(Integer id) {
        LOGGER.info("Method findById was started");
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
        LOGGER.info("Method save was started.");
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        }
    }

    @Override
    public void update(City city) {
        LOGGER.info("Method update was started.");
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(city);
        transaction.commit();

    }

    @Override
    public void deletedById(Integer id) {
        LOGGER.info("Method deletedById was started.");
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int deletedResult = session.createQuery("DELETE FROM City where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();

            if (deletedResult == 0) {
                throw new CityNotFoundException("City with id: " + id + " can't be found!");
            } else {
                System.out.println("Deleted was successful");
            }
        }
    }

    public static CityDao getInstance() {
        return INSTANCE;
    }
}