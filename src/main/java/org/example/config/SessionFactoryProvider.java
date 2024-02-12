package org.example.config;

import org.example.entity.City;
import org.example.entity.Country;
import org.example.entity.CountryLanguage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
    private static org.hibernate.SessionFactory factory;

    public static SessionFactory getSessionFactory(){
        if (factory == null){
            factory = new Configuration().
                    addAnnotatedClass(City.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(CountryLanguage.class).
                    buildSessionFactory();
        }
        return factory;
    }




}
