package org.example.service;
import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.entity.Country;
import org.example.exception.CityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CityService {
    private final CityDao cityDao = CityDao.getInstance();
    private final CountryDao countryDao = CountryDao.getInstance();

  public List<City> getAllCity(){
      return cityDao.findAll();
  }
  public Optional <City> getCityByName(String name){return cityDao.findByName(name);}
    public void addCity(City city, Country country){cityDao.save(city);

    }


  public List<Country> getAllCountry(){return countryDao.findAll();}







}
