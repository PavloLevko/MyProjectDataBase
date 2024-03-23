package org.example.Menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.City;
import org.example.service.CityService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final String HELLO = "Hello! You can put some information from Data Base. " +
            "What would you like to put?\n" +
            "ALL CITY - PRESS 1\n" +
            "CITY BY ID - PRESS 2 \n" +
            "EXIT - PRESS 0";
    private static final String TEXT_AFTER_INPUT = "You input: ";
    private static final String INPUT_CITY_ID = "Please input city id: ";
    private static final String WRONG_DATA = "Wrong input data";
    private static CityService cityService = new CityService();
    private static int howManyTimesWeCallToDB = 12;
    private static int cityId = 56;
    private boolean isRun = true;

    Scanner scanner = new Scanner(System.in);

    public int scan() {
        int input = scanner.nextInt();
        return input;
    }

    public void logic(int input) {
        if (input == 1) {
            System.out.println(TEXT_AFTER_INPUT + input);
            List<City> allCity = cityService.getAllCity();
            System.out.println(allCity);
        } else if (input == 2) {
            System.out.println(TEXT_AFTER_INPUT + input);
            System.out.println(INPUT_CITY_ID);
            int inputCityId = scanner.nextInt();
            String cityById = cityService.getCityById(inputCityId);
            System.out.println(cityById);
        } else {
            System.out.println(WRONG_DATA);
        }
    }

    public void sayHello() {
        System.out.println(HELLO);
    }
}