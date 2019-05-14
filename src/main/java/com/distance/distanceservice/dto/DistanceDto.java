package com.distance.distanceservice.dto;

import lombok.Data;

@Data
public class DistanceDto {
    private String cityOne;
    private String cityTwo;
    private long distance;
}
