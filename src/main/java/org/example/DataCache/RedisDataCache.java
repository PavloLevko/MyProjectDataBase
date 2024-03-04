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
    private CityService service;
    private Jedis jedis;

    public RedisDataCache() {
        jedis = new Jedis("localhost", 6379);
    }

    public Boolean checkDataCacheFromRedis(String kay) {
        boolean present = jedis.exists(kay);
        return present;
    }

     public void setDataToRedisCache(String kay, String value) {
         jedis.setex(kay, CACHE_TIME, value);
     }

     public String getDataFromRedisCache(String kay){
         String dataFromCache = jedis.get(kay);
         return dataFromCache;
     }

     public byte methodCounter(byte couner){
        couner++;
        return couner;
     }

}