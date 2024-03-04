package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.dynamic.DynamicType;
import org.example.DataCache.RedisDataCache;
import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.entity.Country;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CityService {

    private final CityDao cityDao = CityDao.getInstance();
    private final CountryDao countryDao = CountryDao.getInstance();
   private static RedisDataCache redis = new RedisDataCache();
   private static ObjectMapper objectMapper = new ObjectMapper();
    private static final byte CACHE_DATA_START_WRITE = 10;
    private static byte counterGetAllCity = 0;
    private static byte counterCityByName = 0;
    private static byte counterGetCountOfCity = 0;
    private static byte counterGetCityById = 0;
    private static byte counterGetAllCountry = 0;

    public List<City> getAllCity() {
        counterGetAllCity++;
        return cityDao.findAll();
    }

    public Optional<City> getCityByName(String name) {
        counterCityByName++;
        return cityDao.findByName(name);
    }

    public void addCity(City city, Country country) {
        cityDao.save(city);
    }

    public Long getCountOfCity() {
        counterGetCountOfCity++;

        return cityDao.getTotalCount();
    }

    public Optional<City> getCityById(Integer id) throws JsonProcessingException {
        Optional<City> cityById = Optional.empty();
        counterGetCityById++;
        boolean status = validateMethodCounter(counterGetCityById);
        if (status) {
            String dataFromRedisCache = redis.getDataFromRedisCache(id.toString());
            System.out.println("Redis is work");
            return mapperTexttoCity(dataFromRedisCache);
        } else {
            cityById = cityDao.findById(id);
            redis.setDataToRedisCache(id.toString(), cityById.toString());
            System.out.println("Redis dont work");
        }
        return cityById;
    }

    public void updateCity(City city) {
        cityDao.update(city);
    }

    public void deletedCityById(Integer id) {
        cityDao.deletedById(id);
    }

    public List<Country> getAllCountry() {
        counterGetAllCountry++;
        return countryDao.findAll();
    }

    public boolean validateMethodCounter(byte count) {
        if (count >= CACHE_DATA_START_WRITE) {
            return true;
        } else {
            return false;
        }
    }

    public static Optional<City> mapperTexttoCity(String text) throws JsonProcessingException {
        City city = objectMapper.readValue(text, City.class);
        Optional<City> afterMapper = Optional.of(city);
        return afterMapper;
    }


}