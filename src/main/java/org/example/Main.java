package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;
import org.example.config.SessionFactoryProvider;
import org.example.dao.CityDao;
import org.example.entity.City;
import org.example.service.CityService;
import org.hibernate.SessionFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Main {
    private  final SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
    private static final Jedis jedis = new Jedis("localhost", 6379);
    private CityDao cityDao;

    public static void main(String[] args) {



//        jedis.set ("user", "22");
//        String user = jedis.get("user");
//        System.out.println(user);

        Main main = new Main();
        List<City> allCities = main.fetchData(main);
        main.shutdown();


    }
    private List<City> fetchData(Main main) {
        try (org.hibernate.Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            int totalCount = main.cityDao.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(main.cityDao.getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }
    private void shutdown() {
        if (nonNull(sessionFactory)) {
            sessionFactory.close();
        }
        if (nonNull(jedis)) {
            jedis.shutdown();
        }
    }

}