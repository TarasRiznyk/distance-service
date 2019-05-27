package com.distance.distanceservice.dto;

import com.distance.distanceservice.service.impl.CitiesGraphServiceImpl;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class RouteDto {
    private List<String> cities = new ArrayList<>();
    private long distance;

    public void addPath(String city, long distance) {
        cities.add(city);
        this.distance += distance;
    }
}
