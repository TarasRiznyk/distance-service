package com.distance.distanceservice.service.impl;

import com.distance.distanceservice.dto.DistanceDto;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CitiesGraphServiceImplTest {


    @Autowired
    ObjectProvider<DistanceCalculationService> distanceCalculationService;

    @Autowired
    CitiesGraphServiceImpl citiesGraphService = new CitiesGraphServiceImpl(distanceCalculationService);

    @BeforeEach
    void setup() {
        citiesGraphService.addCity(new DistanceDto("A", "B", 10));
        citiesGraphService.addCity(new DistanceDto("B", "C", 20));
        citiesGraphService.addCity(new DistanceDto("A", "C", 40));
        citiesGraphService.addCity(new DistanceDto("C", "D", 40));
    }

    @Test
    void addCityTest() {
        Assert.assertEquals(4, citiesGraphService.getCitiesGraphSnapshot().size());
    }

    @Test
    void calculateDistancesTest() {
        System.out.println("A-B");
        System.out.println(citiesGraphService.calculateDistances("A", "B"));
    }

    @Test
    void calculateDistancesTest2() {
        System.out.println("A-C");
        System.out.println(citiesGraphService.calculateDistances("A", "C"));
    }

    @Test
    void calculateDistancesTest3() {
        System.out.println("B-C");
        System.out.println(citiesGraphService.calculateDistances("B", "C"));
    }

    @Test
    void calculateDistancesTest4() {
        System.out.println("A-D");
        System.out.println(citiesGraphService.calculateDistances("A", "D"));
    }
}