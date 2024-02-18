package org.example.service;
import org.example.dao.CityDao;
import org.example.entity.City;
import org.example.exception.CityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CityService {
    private final CityDao cityDao = CityDao.getInstance();

  public List<City> getAll(){
      return cityDao.findAll();
  }




}
