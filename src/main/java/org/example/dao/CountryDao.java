package org.example.dao;

import org.example.config.SessionFactoryProvider;
import org.example.entity.Country;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CountryDao implements DaoOperation<Country, Integer> {
    private static final CountryDao INSTANCE = new CountryDao();
    private org.hibernate.SessionFactory factory = SessionFactoryProvider.getSessionFactory();

    public CountryDao() {
    }

    @Override
    public List<Country> findAll() {
        Session session = factory.openSession();
        Query<Country> allCountry = session.createQuery("FROM Country ", Country.class);
        return allCountry.getResultList();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        Session session = factory.openSession();
        Query<Country> query = session.createQuery("SELECT c FROM Country c WHERE c.id= :id", Country.class);
        query.setParameter("id", id);
        return Optional.empty();
    }

    public Country findByName(String name) {
        Session session = factory.openSession();
        Query<Country> query = session.createQuery("FROM Country WHERE name = :name", Country.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    public Integer getIdCountryByName(String name) {
        Session session = factory.openSession();
        Query<Country> query = session.createQuery("SELECT c.id FROM Country c WHERE name = :name", Country.class);
        query.setParameter("name", name);
        Country country = (Country) query.getResultList();
        Integer id = country.getId();
        return id;
    }

    @Override
    public void save(Country country) {
        Session session = factory.openSession();
        session.save(country);
    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void deletedById(Integer integer) {

    }

    public static CountryDao getInstance() {
        return INSTANCE;
    }
}