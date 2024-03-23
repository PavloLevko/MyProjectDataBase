package org.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.example.dao.CityDao;
import org.example.dao.CountryDao;
import org.example.entity.City;
import org.example.entity.Country;
import org.example.exception.CityNotFoundException;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class CityServiceTest {

    CityDao cityDao = CityDao.getInstance();
    CityService cityService = new CityService();
    CountryDao countryDao = new CountryDao();


    @Test
    public void getAllCityNotNullTest() {
        List<City> allCity = cityService.getAllCity();
        assertNotNull(allCity);
    }

    @Test
    public void getAllCityCorrectDataTest() {
        List<City> allCity = cityDao.findAll();
        List<City> expected = cityService.getAllCity();
        assertEquals(allCity.size(), expected.size());
    }

    @Test
    public void getCityByNameNoyNullTest() {
        String cityName = "Lviv";
        Optional<City> cityByName = cityService.getCityByName(cityName);
        assertNotNull(cityByName);
    }

    @Test
    public void getCityByNameCorrectDataTest() {
        String cityName = "Lviv";
        Optional<City> expected = cityDao.findByName(cityName);
        Optional<City> cityByName = cityService.getCityByName(cityName);
        assertEquals(expected.get().getName(), cityByName.get().getName());
    }

    @Test
    public void saveCityTest() {
        Country country = countryDao.findByName("Ukraine");
        City cityToSave = new City();
        cityToSave.setDistrict("someDistrict");
        cityToSave.setName("someCity");
        cityToSave.setPopulation(0);
        cityToSave.setCountry(country);
        cityService.addCity(cityToSave);

        Optional<City> actualCity = cityDao.findByName("someCity");

        assertTrue(actualCity.isPresent());
        assertEquals("someCity", actualCity.get().getName());
        assertEquals("someDistrict", actualCity.get().getDistrict());
        assertEquals(0, actualCity.get().getPopulation());
    }

    @Test
    public void getCountOfCityTest() {
        Long expected = cityDao.getTotalCount();
        Long countOfCity = cityService.getCountOfCity();
        assertEquals(expected, countOfCity);
    }

    @Test
    public void getCityByIdExceptionTest() {
        Integer cityId = null;
        assertThrowsExactly(CityNotFoundException.class, () -> cityService.deletedCityById(cityId));
    }

    @Test
    public void getCityByIdNotNulTest() {
        Integer cityId = 1;
        Optional<City> city = cityDao.findById(cityId);
        assertNotNull(city);
    }

    @Test
    public void updateCityTest() {
        Country country = countryDao.findByName("Ukraine");
        City city = new City();
        city.setName("JavaLends");  //you must add new name for run this test
        city.setDistrict("Java");
        city.setPopulation(1000);
        city.setCountry(country);

        cityService.addCity(city);

        city.setPopulation(5000);

        cityService.updateCity(city);

        Optional<City> actual = cityService.getCityByName("JavaLends");


        assertEquals(5000, actual.get().getPopulation());
    }

    @Test
    public void deletedCityByIdExceptionTest() {
        Integer cityId = null;
        assertThrowsExactly(CityNotFoundException.class, () -> cityService.deletedCityById(cityId));
    }

    @Test
    public void deletedCityByIdTest() {
        String cityName = "nameOfCity";
        Country country = countryDao.findByName("Ukraine");
        City cityToSave = new City();
        cityToSave.setDistrict("someDistrict");
        cityToSave.setName(cityName);
        cityToSave.setPopulation(0);
        cityToSave.setCountry(country);
        cityService.addCity(cityToSave);

        Optional<City> actualCity = cityDao.findByName(cityName);
        Integer id = actualCity.get().getId();

        cityService.deletedCityById(id);

        Optional<City> cityByName = cityService.getCityByName(cityName);

        assertFalse(cityByName.isPresent());

    }

    @Test
    public void getAllCountryNotNullTest(){
        List<Country> allCountry = cityService.getAllCountry();

        assertNotNull(allCountry);
    }


}