package org.example.service;

import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.entity.Country;

import java.util.List;
import java.util.Optional;

public class CityService {
    private final CityDao cityDao = CityDao.getInstance();
    private final CountryDao countryDao = CountryDao.getInstance();

    public List<City> getAllCity() {
        return cityDao.findAll();
    }

    public Optional<City> getCityByName(String name) {
        return cityDao.findByName(name);
    }

    public void addCity(City city, Country country) {
        cityDao.save(city);
    }

    public Long getCountOfCity() {
        return cityDao.getTotalCount();
    }

    public Optional<City> getCityById(Integer id) {
        return cityDao.findById(id);
    }

    public void updateCity(City city) {
        cityDao.update(city);
    }

    public void deletedCityById(Integer id) {
        cityDao.deletedById(id);
    }

    public List<Country> getAllCountry() {
        return countryDao.findAll();
    }
}