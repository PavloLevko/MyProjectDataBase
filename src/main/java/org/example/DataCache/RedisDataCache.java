package org.example.DataCache;

import org.example.entity.City;
import org.example.service.CityService;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class RedisDataCache {
    static final Long CACHE_TIME = 20L;
    private CityService service = new CityService();
    private Jedis jedis;

    public RedisDataCache() {
        jedis = new Jedis("localhost", 6379);
    }

    public String getDataFromCache(String kay) {

        if (jedis.exists(kay)) {
            return jedis.get(kay);
        } else {
            Optional<City> cityByName = service.getCityByName(kay);
            String city = cityByName.toString();
            jedis.setex(kay, CACHE_TIME, city);
        }
        return kay;
    }

}