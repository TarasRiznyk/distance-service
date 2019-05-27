package com.distance.distanceservice.service.impl;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class DistanceCalculationService {
    private final Map<CitiesGraphServiceImpl.Neighbour, Set<CitiesGraphServiceImpl.Neighbour>> citiesGraph;

    private List<Stack<CitiesGraphServiceImpl.Neighbour>> allCitiesPath = new ArrayList<>();

    public List<Stack<CitiesGraphServiceImpl.Neighbour>> calculateDistances(String cityOne, String cityTwo, Stack<CitiesGraphServiceImpl.Neighbour> citiesPath) {
        for (CitiesGraphServiceImpl.Neighbour neighbour : citiesGraph.getOrDefault(cityOne , Collections.emptySet())) {
            if (!citiesPath.contains(neighbour)) {
                citiesPath.add(neighbour);
            } else  {
                continue;
            }
            if (neighbour.getCity().equals(cityTwo)) {
                Stack<CitiesGraphServiceImpl.Neighbour> temp = new Stack<>();
                temp.addAll(citiesPath);
                System.out.println("Found path " + temp);
                allCitiesPath.add(temp);
            } else {
                calculateDistances(neighbour.getCity(), cityTwo, citiesPath);
            }
            citiesPath.remove(neighbour);

        }
        return allCitiesPath;
    }
}
