package org.example;
import org.example.DataCache.RedisDataCache;
import org.example.config.SessionFactoryProvider;
import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.service.CityService;

import java.util.List;

public class Main {
    private static   final org.hibernate.SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    private static CityDao cityDao = CityDao.getInstance();
    private static CountryDao countryDao = CountryDao.getInstance();
    private static CityService cityService = new CityService();
    private static RedisDataCache redis = new RedisDataCache();

    public static void main(String[] args) {

//        long startMysql = System.currentTimeMillis();
//        List<City> all = cityDao.findAll();
//        long stopMysql = System.currentTimeMillis();
//        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        String city = redis.getDataFromCache("Lviv");
        System.out.println(city);



//
//        Country ukraine = countryDao.findByName("Ukraine");
//
//
//        City busk = new City();
//          busk.setName("Yavoryv");
//          busk.setCountry(ukraine);
//          busk.setDistrict("Lviv");
//          busk.setPopulation(4000);
//
//
//         cityDao.save(busk);

//        Optional<City> busk = cityDao.findByName("Busk");
//        System.out.println(busk);


//        jedis.set ("user", "22");
//        String user = jedis.get("user");
//        System.out.println(user);
//        List<City> all = cityDao.findAll();
//        System.out.println(all);
//
//        Optional<City> lviv = cityDao.findByName("Lviv");
//        System.out.println(lviv);

//        Optional<Country> ukraine1 = countryDao.findByName("Ukrane");
//        System.out.println(ukraine1);

//        Optional<City> lviv = cityService.getCityByName("Lviv");
//        System.out.println(lviv);



//        City oran = cityDao.findByName("Oran");
//        System.out.println(oran.toString());




    }


}