package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.service.CityService;

public class Main {
    private static CityService cityService = new CityService();
    private static int howManyTimesWeCallToDB = 12;
    private static int cityId = 56;
    public static void main(String[] args) throws JsonProcessingException {
        for (int i = 0; i < howManyTimesWeCallToDB; i++) {
           String cityById = cityService.getCityById(cityId);
            System.out.println(System.nanoTime());
            System.out.println(cityById);
        }
    }
}