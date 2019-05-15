package com.distance.distanceservice.service.impl;

import com.distance.distanceservice.service.ICitiesGraphService;
import com.distance.distanceservice.service.IDistanceServiceCalcultaion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
@Component
public class DistanceCalculationServiceImpl implements IDistanceServiceCalcultaion {

    private final ICitiesGraphService citiesGraphService;

    private ObjectProvider<DistanceCalculationService> distanceCalculatorProvider;

    public void reCalculateDistances(String cityOne, String cityTwo, Stack<CitiesGraphServiceImpl.Neighbour> citiesPath, List<Stack<CitiesGraphServiceImpl.Neighbour>> allCitiesPath) {
        distanceCalculatorProvider.getObject(citiesGraphService.getCitiesGraphSnapshot());
        var citiesGraphSnapshot = citiesGraphService.getCitiesGraphSnapshot();

        for (CitiesGraphServiceImpl.Neighbour neighbour : citiesGraphSnapshot.get(cityOne)) {
            if (neighbour.getCity().equals(cityTwo)) {
                Stack<CitiesGraphServiceImpl.Neighbour> temp = new Stack<>();
                temp.addAll(citiesPath);
                allCitiesPath.add(temp);
            } else if (!citiesPath.contains(neighbour)) {
                citiesPath.add(neighbour);
                reCalculateDistances(neighbour.getCity(), cityTwo, citiesPath, allCitiesPath);
                citiesPath.remove(neighbour);
            }
        }
    }

    private void calculateCitiesPath(String cityOne, String cityTwo, Stack<CitiesGraphServiceImpl.Neighbour> citiesPath, List<Stack<CitiesGraphServiceImpl.Neighbour>> allCitiesPath) {

    }
}
