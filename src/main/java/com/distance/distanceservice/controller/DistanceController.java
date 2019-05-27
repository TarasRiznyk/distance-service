package com.distance.distanceservice.controller;

import com.distance.distanceservice.dto.DistanceDto;
import com.distance.distanceservice.dto.RouteDto;
import com.distance.distanceservice.service.ICitiesGraphService;
import com.distance.distanceservice.service.impl.CitiesGraphServiceImpl;
import com.distance.distanceservice.service.impl.DistanceCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class DistanceController {

    private final ICitiesGraphService citiesGraphService;

    @PostMapping(value = "/distance")
    public void addRoute(@RequestBody DistanceDto distanceDto) {
        citiesGraphService.addCity(distanceDto);
    }

    @GetMapping(value = "/distance/{cityOne}/{cityTwo}")
    public List<RouteDto> getDistance(@PathVariable String cityOne, @PathVariable String cityTwo) {
        var distances = citiesGraphService.calculateDistances(cityOne, cityTwo);
        return distances.stream().map(pathEntries -> {
                    var routeDto = new RouteDto();
                    pathEntries.forEach(pathEntry -> routeDto.addPath(pathEntry.getCity(), pathEntry.getDistance())
                    );
                    return routeDto;
                }
        ).collect(Collectors.toList());
    }
}
