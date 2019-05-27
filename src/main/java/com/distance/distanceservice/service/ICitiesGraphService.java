package com.distance.distanceservice.service;

import com.distance.distanceservice.dto.DistanceDto;
import com.distance.distanceservice.service.impl.CitiesGraphServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public interface ICitiesGraphService {
    void addCity(DistanceDto cityDistance);

    Map<String, Set<CitiesGraphServiceImpl.Neighbour>> getCitiesGraphSnapshot();

    List<Stack<CitiesGraphServiceImpl.Neighbour>> calculateDistances(String cityOne, String cityTwo);
}