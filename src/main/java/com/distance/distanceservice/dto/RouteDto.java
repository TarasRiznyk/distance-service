package com.distance.distanceservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RouteDto {
    private Set<String> cities;
    private long distance;
}
