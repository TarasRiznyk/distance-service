package com.distance.distanceservice.service.impl;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class DistanceCalculationService {
    private final Map<String, Set<CitiesGraphServiceImpl.Neighbour>> citiesGraph;

    private List<Stack<CitiesGraphServiceImpl.Neighbour>> allCitiesPath = new ArrayList<>();

    public List<Stack<CitiesGraphServiceImpl.Neighbour>> calculateDistances(String cityOne, String cityTwo, Stack<CitiesGraphServiceImpl.Neighbour> citiesPath) {
        for (CitiesGraphServiceImpl.Neighbour neighbour : citiesGraph.get(cityOne)) {
            if (neighbour.getCity().equals(cityTwo)) {
                Stack<CitiesGraphServiceImpl.Neighbour> temp = new Stack<>();
                temp.addAll(citiesPath);
                allCitiesPath.add(temp);
            } else if (!citiesPath.contains(neighbour)) {
                citiesPath.add(neighbour);
                calculateDistances(neighbour.getCity(), cityTwo, citiesPath);
                citiesPath.remove(neighbour);
            }
        }
        return allCitiesPath;
    }
}
