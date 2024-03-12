package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.bytebuddy.dynamic.DynamicType;
import org.example.DataCache.RedisDataCache;
import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.dao.DaoOperation;
import org.example.entity.City;
import org.example.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
    private final CityDao cityDao = CityDao.getInstance();
    private final CountryDao countryDao = CountryDao.getInstance();
    private static RedisDataCache redis = RedisDataCache.getInstance();
    private static final byte CACHE_DATA_START_WRITE = 10;
    private static byte counterGetCityById = 0;


    public List<City> getAllCity() {
        LOGGER.info("Method getAllCity started");
        return cityDao.findAll();
    }

    public Optional<City> getCityByName(String name) {
        LOGGER.info("Method getCityByName started");
        return cityDao.findByName(name);
    }

    public void addCity(City city) {
        LOGGER.info("Method addCountry started");
        cityDao.save(city);
    }

    public Long getCountOfCity() {
        LOGGER.info("Method getCountOfCity started");
        return cityDao.getTotalCount();
    }

    public String getCityById(Integer id) {
        LOGGER.info("Method getCityById started");
        Optional<City> cityById = Optional.empty();
        counterGetCityById++;
        boolean status = validateMethodCounter(counterGetCityById);
        if (status) {
            LOGGER.info("Redis was started");
            String dataFromRedisCache = redis.getDataFromRedisCache(id.toString());

            return dataFromRedisCache;
        } else {
            cityById = cityDao.findById(id);
            redis.setDataToRedisCache(id.toString(), cityById.toString());

        }
        return cityById.toString();
    }

    public void updateCity(City city) {
        LOGGER.info("Method updateCity started");
        cityDao.update(city);
    }

    public void deletedCityById(Integer id) {
        LOGGER.info("Method deletedCityById started");
        cityDao.deletedById(id);
    }

    public List<Country> getAllCountry() {
        LOGGER.info("Method getAllCountry started");
        return countryDao.findAll();
    }

    public boolean validateMethodCounter(byte count) {
        LOGGER.info("Method validateMethodCounter work");
        if (count >= CACHE_DATA_START_WRITE) {
            return true;
        } else {
            return false;
        }
    }
}