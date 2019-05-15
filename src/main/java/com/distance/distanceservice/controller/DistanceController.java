package com.distance.distanceservice.controller;

import com.distance.distanceservice.dto.DistanceDto;
import com.distance.distanceservice.service.ICitiesGraphService;
import com.distance.distanceservice.service.impl.DistanceCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class DistanceController {

    private final ICitiesGraphService citiesGraphService;
    private final DistanceCalculationService distanceCalculationService;

    @PostMapping(value = "/path")
    public void addRoute(@RequestBody DistanceDto distanceDto) {
        citiesGraphService.addCity(distanceDto);
    }

    @GetMapping(value = "/path")
    public void greeting() {
    }
}
