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

  public City getById (Long id){
     return cityDao.findById(id).
             orElseThrow(()-> new CityNotFoundException(String.
                     format("City not found! id = %d", id)));
  }

  public void deletedById(Long id){
  }

}
