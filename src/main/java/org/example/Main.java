package org.example;
import org.example.config.SessionFactoryProvider;
import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.entity.Country;
import org.hibernate.Session;
import org.hibernate.query.Query;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class Main {
    private static   final org.hibernate.SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private static final Jedis jedis = new Jedis("localhost", 6379);
    private static CityDao cityDao = new CityDao();
    private static CountryDao countryDao = new CountryDao();

    public static void main(String[] args) {


          City city = new City();


//        jedis.set ("user", "22");
//        String user = jedis.get("user");
//        System.out.println(user);
//        List<City> all = cityDao.findAll();
//        System.out.println(all);

       Optional <City> cityName = cityDao.findByName("Lviv");
        System.out.println(cityName.toString());


//        City oran = cityDao.findByName("Oran");
//        System.out.println(oran.toString());




    }


}