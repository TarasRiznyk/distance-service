package com.distance.distanceservice.controller;

import com.distance.distanceservice.dto.DistanceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DistanceController {

    @PostMapping(value = "/path")
    public void addRout(@RequestBody DistanceDto distanceDto) {
    }

    @GetMapping(value = "/path")
    public void greeting() {
    }
}
