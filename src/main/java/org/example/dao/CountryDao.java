package org.example.dao;

import org.example.config.SessionFactoryProvider;
import org.example.entity.Country;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryDao {
    private final SessionFactory sessionFactory;

    public CountryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

        public List<Country> getAll () {
            Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c", Country.class);
            return query.list();
        }
    }


