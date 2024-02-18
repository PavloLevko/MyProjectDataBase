package org.example.dao;

import org.example.config.SessionFactoryProvider;
import org.example.entity.City;
import org.example.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CountryDao implements DaoOperation<Country,Integer>{
    private org.hibernate.SessionFactory factory = SessionFactoryProvider.getSessionFactory();


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

    @Override
    public void save(Country country) {

    }

    @Override
    public void update(Country country) {

    }

    @Override
    public void deletedById(Integer integer) {

    }
}


