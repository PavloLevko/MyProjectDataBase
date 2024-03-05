package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.DataCache.RedisDataCache;
import org.example.config.SessionFactoryProvider;
import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.service.CityService;

import java.util.List;
import java.util.Optional;

public class Main {
    private static   final org.hibernate.SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    private static CityDao cityDao = CityDao.getInstance();
    private static CountryDao countryDao = CountryDao.getInstance();
    private static CityService cityService = new CityService();
    private static RedisDataCache redis = new RedisDataCache();

    public static void main(String[] args) throws JsonProcessingException {

//        long startMysql = System.currentTimeMillis();
//        List<City> all = cityDao.findAll();
//        long stopMysql = System.currentTimeMillis();
//        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        for (int i = 0; i <13 ; i++) {


           String cityById = cityService.getCityById(56);
            System.out.println(cityById);
        }

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