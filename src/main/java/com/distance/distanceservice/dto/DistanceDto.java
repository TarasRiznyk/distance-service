package com.distance.distanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DistanceDto {
    private final String cityOne;
    private final String cityTwo;
    private final long distance;
}
